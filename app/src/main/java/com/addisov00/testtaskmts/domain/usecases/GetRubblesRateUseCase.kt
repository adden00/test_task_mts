package com.addisov00.testtaskmts.domain.usecases

import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.domain.CurrencyRepository
import com.addisov00.testtaskmts.presentation.main_screen.models.CurrencyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRubblesRateUseCase @Inject constructor(private val repository: CurrencyRepository) {
    operator fun invoke(): Flow<CurrencyData> {
        return repository.getCurrencies().map { baseCurrencyRates ->
            val rubleRate = baseCurrencyRates.currencyList.find {
                it.name == Constants.RUB
            }?.value ?: 0f
            baseCurrencyRates.copy(
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
}

