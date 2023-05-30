package com.addisov00.testtaskmts.presentation.main_screen

sealed class ScreenEvent {
    data class SearchCurrency(val query: String) : ScreenEvent()
    object SubscribeOnCurrencies : ScreenEvent()
    object StopSearch : ScreenEvent()
    object UpdateCurrencies : ScreenEvent()
}