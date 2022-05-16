package com.rikson.gestordeventas.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class InvoiceWithClientAndCompany(
    @Embedded val invoiceEntity: InvoiceEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val companyEntity: CompanyEntity,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "id"
    )
    val clientEntity: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "invoiceId"
    )
    val products:List<ProductEntity>
)