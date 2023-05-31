package com.addisov00.testtaskmts.common.di

import android.content.Context
import com.addisov00.testtaskmts.common.utills.InternetChecker
import com.addisov00.testtaskmts.domain.CurrencyRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {
    fun currencyRepository(): CurrencyRepository
    fun internetChecker(): InternetChecker

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}