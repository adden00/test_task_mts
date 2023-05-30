package com.addisov00.testtaskmts.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "currency_name")
    val currencyName: String,

    @ColumnInfo(name = "currency_rate")
    val currencyRate: Float
)