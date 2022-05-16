package com.rikson.gestordeventas.domain.model

sealed class InvoiceEvent{
    data class NameChanged(val name:String): InvoiceEvent()
    data class LastNameChanged(val lastName:String): InvoiceEvent()
    data class IdentifierChanged(val identifier:String) : InvoiceEvent()
    data class CompanyNameChanged(val companyName:String) : InvoiceEvent()
    data class CompanyRifChanged(val companyRif:String): InvoiceEvent()
    object Submit: InvoiceEvent()
}
