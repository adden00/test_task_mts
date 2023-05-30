package com.addisov00.testtaskmts.domain.usecases

import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.domain.CurrencyRepository
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import javax.inject.Inject

class GetRubblesRateUseCase @Inject constructor(private val repository: CurrencyRepository) {
    suspend operator fun invoke(): CurrencyData {
        val baseCurrencyRates = repository.getCurrencies()
        val rubleRate = baseCurrencyRates.currencyList.find {
            it.name == Constants.rub
        }?.value ?: throw Exception("Ruble rate didn`t received!")
        return baseCurrencyRates.copy(
            currencyList = baseCurrencyRates.currencyList.map {
                it.copy(
                    value = String
                        .format("%.3f", (rubleRate / it.value))
                        .replace(",", ".")
                        .toFloat()
                )
            }
        )
    }
}

