package com.rikson.gestordeventas.domain.model

import com.rikson.gestordeventas.data.entities.ClientEntity
import com.rikson.gestordeventas.data.entities.CompanyEntity
import com.rikson.gestordeventas.data.entities.InvoiceEntity
import com.rikson.gestordeventas.data.entities.ProductEntity

data class InvoiceModel(
    var company: CompanyEntity = CompanyEntity(),
    var client: ClientEntity = ClientEntity(),
    var total:Double = 0.0,
    var products:ArrayList<ProductEntity> = arrayListOf()
)

