package com.rikson.gestordeventas.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_company")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "bussines_name") val bussinessName:String = "",
    @ColumnInfo(name = "rif") val rif:String = ""
)