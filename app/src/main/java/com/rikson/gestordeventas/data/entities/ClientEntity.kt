package com.rikson.gestordeventas.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_client")
data class ClientEntity (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name:String = "",
    @ColumnInfo(name = "last_name")
    val lastName:String = "",
    val identifier:String = ""
)