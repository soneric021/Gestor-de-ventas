package com.rikson.gestordeventas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity(tableName = "tb_invoice")
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    var clientId: Int = 0,
    var companyId: Int = 0,
    val total:Double
)

class InvalidInvoiceException(message:String) : Exception(message)
