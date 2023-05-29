package com.addisov00.testtaskmts.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.addisov00.testtaskmts.domain.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState>
        get() = _screenState.asStateFlow()

    init {
        loadCurrencies()
    }


    fun loadCurrencies() {
        _screenState.value = _screenState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = repository.getCurrencies()
            _screenState.value =
                _screenState.value.copy(
                    currentCurrencyList = result.currencyList,
                    isLoading = false,
                    updatedDate = result.updatedDate
                )
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