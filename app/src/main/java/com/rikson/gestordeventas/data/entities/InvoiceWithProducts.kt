package com.rikson.gestordeventas.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class InvoiceWithProducts(
    @Embedded val invoiceEntity: InvoiceEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "InvoiceId"
    )
    val products:List<ProductEntity>
)