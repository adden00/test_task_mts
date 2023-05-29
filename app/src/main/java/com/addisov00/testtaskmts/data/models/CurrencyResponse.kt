package com.addisov00.testtaskmts.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("success")
    val resultSuccess: Boolean,

    @SerialName("date")
    val date: String,

    @SerialName("rates")
    val rates: Map<String, Float>
)