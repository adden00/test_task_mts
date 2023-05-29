package com.addisov00.testtaskmts.domain

import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData

interface CurrencyRepository {
    suspend fun getCurrencies(): CurrencyData
}