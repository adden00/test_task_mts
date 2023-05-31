package com.addisov00.testtaskmts.presentation.main_screen.states

sealed class ScreenEffects {
    object ShowNoInternetMessage : ScreenEffects()
    object ShowErrorWhileLoading : ScreenEffects()
    object Init : ScreenEffects()
}