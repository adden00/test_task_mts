package com.addisov00.testtaskmts.common.di

import com.addisov00.testtaskmts.data.CurrencyRepositoryImpl
import com.addisov00.testtaskmts.data.local.CurrencyDao
import com.addisov00.testtaskmts.data.network.CurrencyApiClient
import com.addisov00.testtaskmts.domain.CurrencyRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideCurrencyRepo(api: CurrencyApiClient, dao: CurrencyDao): CurrencyRepository =
        CurrencyRepositoryImpl(api, dao)
}