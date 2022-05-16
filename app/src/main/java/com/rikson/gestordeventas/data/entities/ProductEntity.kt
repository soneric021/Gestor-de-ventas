package com.rikson.gestordeventas.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "code") val code:String = "",
    @ColumnInfo(name = "description") val description:String = "",
    @ColumnInfo(name = "quantity") val quantity:Int = 0,
    @ColumnInfo(name = "price") val price:Double = 0.0,
    var invoiceId:Int = 0
)
