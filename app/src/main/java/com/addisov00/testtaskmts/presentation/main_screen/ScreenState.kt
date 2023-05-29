package com.addisov00.testtaskmts.presentation.main_screen

import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem

data class ScreenState(
    val currentCurrencyList: List<CurrencyItem> = listOf(),
    val isLoading: Boolean = false,
    val updatedDate: String = ""
)