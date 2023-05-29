package com.addisov00.testtaskmts.data

import com.addisov00.testtaskmts.domain.CurrencyRepository
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApiClient) :
    CurrencyRepository {

    override suspend fun getCurrencies(): CurrencyData {
        val result = api.getCurrency()
        if (result.resultSuccess) {
            val resultList = mutableListOf<CurrencyItem>()
            for ((name, value) in result.rates) {
                resultList.add(CurrencyItem(name, value))
            }
            return CurrencyData(result.date, resultList)
        } else
            throw Exception("error while loading") //TODO: создать свое иключение
    }
}