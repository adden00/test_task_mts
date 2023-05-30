package com.addisov00.testtaskmts.data

import com.addisov00.testtaskmts.data.local.CurrencyDao
import com.addisov00.testtaskmts.data.network.CurrencyApiClient
import com.addisov00.testtaskmts.domain.CurrencyRepository
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(private val api: CurrencyApiClient, private val dao: CurrencyDao) :
    CurrencyRepository {

    override suspend fun updateCurrencies() {
        val result = api.getCurrency()
        if (result.resultSuccess) {
            val resultList = mutableListOf<CurrencyItem>()
            for ((name, value) in result.rates) {
                resultList.add(CurrencyItem(name, value))
            }
            dao.clearCurrencies()
            dao.insertCurrencies(CurrencyData(result.date, resultList).toEntity())
        } else
            throw Exception("error while loading") //TODO: создать свое иключение
    }

    override fun getCurrencies(): Flow<CurrencyData> =
        dao.getAllCurrencies().map {
            it.toCurrencyData()
        }
}