package com.addisov00.testtaskmts.common.di

import com.addisov00.testtaskmts.data.CurrencyApiClient
import com.addisov00.testtaskmts.data.CurrencyRepositoryImpl
import com.addisov00.testtaskmts.domain.CurrencyRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideCurrencyRepo(api: CurrencyApiClient): CurrencyRepository =
        CurrencyRepositoryImpl(api)
}