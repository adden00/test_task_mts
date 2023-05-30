package com.addisov00.testtaskmts.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_currencies")
data class AllCurrenciesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "updated_date")
    val updatedDate: String,

    @ColumnInfo(name = "currencies")
    val currencies: List<CurrencyEntity>
)