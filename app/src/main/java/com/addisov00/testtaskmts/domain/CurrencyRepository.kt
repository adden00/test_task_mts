package com.addisov00.testtaskmts.domain

import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun updateCurrencies()
    fun getCurrencies(): Flow<CurrencyData>
}