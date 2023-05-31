package com.addisov00.testtaskmts.data

import com.addisov00.testtaskmts.data.local.entity.AllCurrenciesEntity
import com.addisov00.testtaskmts.data.local.entity.CurrencyEntity
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyItem

fun AllCurrenciesEntity?.toCurrencyData(): CurrencyData =
    CurrencyData(
        this?.updatedDate ?: "",
        this?.currencies?.map {
            it.toCurrencyItem()
        } ?: listOf()
    )

fun CurrencyEntity.toCurrencyItem(): CurrencyItem =
    CurrencyItem(
        this.currencyName,
        this.currencyRate
    )

fun CurrencyData.toEntity(): AllCurrenciesEntity =
    AllCurrenciesEntity(
        id = null,
        updatedDate = this.updatedDate,
        currencies = this.currencyList.map {
            it.toEntity()
        }
    )

fun CurrencyItem.toEntity(): CurrencyEntity =
    CurrencyEntity(
        this.name,
        this.value
    )