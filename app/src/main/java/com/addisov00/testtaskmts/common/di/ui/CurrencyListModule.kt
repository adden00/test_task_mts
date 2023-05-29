package com.addisov00.testtaskmts.common.di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.addisov00.testtaskmts.common.di.ViewModelKey
import com.addisov00.testtaskmts.presentation.main_screen.MainViewModel
import com.addisov00.testtaskmts.presentation.main_screen.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface CurrencyListModuleBinds {

    @Binds
    fun bindViewModelFactory(impl: MainViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMessengerViewModel(impl: MainViewModel): ViewModel
}