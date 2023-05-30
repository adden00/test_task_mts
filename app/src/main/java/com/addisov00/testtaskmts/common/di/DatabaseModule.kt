package com.addisov00.testtaskmts.common.di

import android.content.Context
import androidx.room.Room
import com.addisov00.testtaskmts.common.Constants
import com.addisov00.testtaskmts.data.local.CurrencyDao
import com.addisov00.testtaskmts.data.local.CurrencyDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): CurrencyDataBase =
        Room.databaseBuilder(context, CurrencyDataBase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCurrencyDao(database: CurrencyDataBase): CurrencyDao =
        database.getDao()
}