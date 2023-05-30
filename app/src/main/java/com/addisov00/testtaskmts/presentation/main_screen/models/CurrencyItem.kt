package com.addisov00.testtaskmts.presentation.main_screen.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyItem(
    val name: String,
    val value: Float
) : Parcelable