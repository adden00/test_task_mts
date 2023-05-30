package com.addisov00.testtaskmts.presentation.main_screen

import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem

data class ScreenState(
    val currentCurrencyList: List<CurrencyItem>? = null,
    val searchingCurrencyList: List<CurrencyItem> = listOf(),
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val updatedDate: String = ""
)