package com.addisov00.testtaskmts.data.local

import androidx.room.TypeConverter
import com.addisov00.testtaskmts.data.local.entity.CurrencyEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CurrencyTypeConverters {

    @TypeConverter
    fun fromCurrency(value: List<CurrencyEntity>) = Json.encodeToString(value)

    @TypeConverter
    fun toCurrency(value: String) = Json.decodeFromString<List<CurrencyEntity>>(value)
}