package com.addisov00.testtaskmts.presentation.states

sealed class ScreenEffects {
    object ShowNoInternetMessage : ScreenEffects()
    object ShowErrorWhileLoading : ScreenEffects()
    object Init : ScreenEffects()
}