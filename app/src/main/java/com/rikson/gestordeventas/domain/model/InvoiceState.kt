package com.rikson.gestordeventas.domain.model

import com.rikson.gestordeventas.data.entities.InvoiceEntity
import com.rikson.gestordeventas.data.entities.ProductEntity

data class InvoiceState(
    var invoiceEntity: InvoiceModel = InvoiceModel(),
    var canContinue:Boolean = false,
    var showProductError:String = "",
    var codeError:String = "",
    var quantityError:String = "",
    var priceError:String = "",
    var descriptionError:String = "",
    var productEntity: ProductEntity = ProductEntity(),
    var nameError:String = "",
    var lastNameError:String = "",
    var identifierError:String = "",
    var companyNameError:String = "",
    var companyRifError:String = "",
    var invoiceId:Int = 0,
    var productAdded:Boolean = false
)
