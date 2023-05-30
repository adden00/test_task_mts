package com.addisov00.testtaskmts.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.addisov00.testtaskmts.domain.usecases.GetRubblesRateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(private val getRubblesRateUseCase: GetRubblesRateUseCase) :
    ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState>
        get() = _screenState.asStateFlow()


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

            is ScreenEvent.LoadCurrencies -> {
                _screenState.value = _screenState.value.copy(isLoading = true, isSearching = false)
                viewModelScope.launch {
                    val result = getRubblesRateUseCase()
                    _screenState.value =
                        _screenState.value.copy(
                            currentCurrencyList = result.currencyList,
                            isLoading = false,
                            updatedDate = result.updatedDate
                        )
                }
            }

            is ScreenEvent.StopSearch -> {
                _screenState.value = _screenState.value.copy(
                    isSearching = false,
                    searchingCurrencyList = listOf()
                )
            }
        }

    }
}

@Suppress("UNCHECKED_CAST")
open class MainViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: error("ViewModel class $modelClass not found")
        return viewModelProvider.get() as T
    }
}