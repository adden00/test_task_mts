package com.addisov00.testtaskmts.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.addisov00.testtaskmts.data.local.entity.AllCurrenciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM all_currencies")
    fun getAllCurrencies(): Flow<AllCurrenciesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: AllCurrenciesEntity)

    @Query("DELETE FROM all_currencies")
    suspend fun clearCurrencies()

}