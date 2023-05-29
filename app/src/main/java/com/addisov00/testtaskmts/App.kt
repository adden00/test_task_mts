package com.addisov00.testtaskmts

import android.app.Application
import android.content.Context
import com.addisov00.testtaskmts.common.di.AppComponent
import com.addisov00.testtaskmts.common.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

fun Context.getAppComponent(): AppComponent = (this.applicationContext as App).appComponent