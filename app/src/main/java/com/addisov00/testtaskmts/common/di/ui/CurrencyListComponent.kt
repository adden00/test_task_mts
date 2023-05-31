package com.addisov00.testtaskmts.common.di.ui

import com.addisov00.testtaskmts.common.di.AppComponent
import com.addisov00.testtaskmts.common.di.ScreenScope
import com.addisov00.testtaskmts.presentation.main_screen.MainFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [CurrencyListModuleBinds::class]
)
interface CurrencyListComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CurrencyListComponent
    }
}