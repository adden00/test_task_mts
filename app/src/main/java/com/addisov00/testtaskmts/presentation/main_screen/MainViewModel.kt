package com.addisov00.testtaskmts.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.addisov00.testtaskmts.common.utills.InternetChecker
import com.addisov00.testtaskmts.common.utills.WrongRequestException
import com.addisov00.testtaskmts.domain.CurrencyRepository
import com.addisov00.testtaskmts.domain.usecases.GetRubblesRateUseCase
import com.addisov00.testtaskmts.presentation.main_screen.states.ScreenEffects
import com.addisov00.testtaskmts.presentation.main_screen.states.ScreenEvent
import com.addisov00.testtaskmts.presentation.main_screen.states.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRubblesRateUseCase: GetRubblesRateUseCase,
    private val repository: CurrencyRepository,
    private val internetChecker: InternetChecker
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState>
        get() = _screenState.asStateFlow()

    private val _screenEffect = MutableStateFlow<ScreenEffects>(ScreenEffects.Init)
    val screenEffects: StateFlow<ScreenEffects>
        get() = _screenEffect.asStateFlow()

    init {
        newEvent(ScreenEvent.UpdateCurrencies)
    }

    fun newEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.SearchCurrency -> {
                _screenState.value = _screenState.value.copy(
                    isSearching = true,
                    searchingCurrencyList = _screenState.value.currentCurrencyList?.filter {
                        event.query.trim().lowercase() in it.name.trim().lowercase()
                    } ?: listOf()
                )
            }

            is ScreenEvent.SubscribeOnCurrencies -> {
                viewModelScope.launch {
                    getRubblesRateUseCase().collect {
                        _screenState.value = _screenState.value.copy(
                            currentCurrencyList = it.currencyList,
                            updatedDate = it.updatedDate
                        )
                    }
                }
            }

            is ScreenEvent.StopSearch -> {
                _screenState.value = _screenState.value.copy(
                    isSearching = false,
                    searchingCurrencyList = listOf()
                )
            }

            is ScreenEvent.UpdateCurrencies -> {
                _screenState.value =
                    _screenState.value.copy(isLoading = true, isSearching = false)
                _screenEffect.value = ScreenEffects.Init
                if (!internetChecker.isOnline()) {
                    _screenEffect.value = ScreenEffects.ShowNoInternetMessage
                    _screenState.value = _screenState.value.copy(isLoading = false)

                } else {
                    viewModelScope.launch {
                        try {
                            repository.updateCurrencies()
                        } catch (e: WrongRequestException) {
                            _screenEffect.value = ScreenEffects.ShowErrorWhileLoading
                        } catch (e: Exception) {
                            _screenEffect.value = ScreenEffects.ShowErrorWhileLoading
                        }
                        _screenState.value = _screenState.value.copy(isLoading = false)
                    }
                }
            }
        }
    }
}


