package com.addisov00.testtaskmts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.addisov00.testtaskmts.data.local.entity.AllCurrenciesEntity
import com.addisov00.testtaskmts.data.local.entity.CurrencyEntity

@Database(entities = [AllCurrenciesEntity::class, CurrencyEntity::class], version = 1)
@TypeConverters(CurrencyTypeConverters::class)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun getDao(): CurrencyDao
}