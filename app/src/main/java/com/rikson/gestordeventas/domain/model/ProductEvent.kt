package com.rikson.gestordeventas.domain.model

sealed class ProductEvent{
    data class CodeChanged(val code:String): ProductEvent()
    data class DescriptionChanged(val description:String): ProductEvent()
    data class PriceChanged(val price:Double) : ProductEvent()
    data class QuantityChanged(val quantity:Int) : ProductEvent()
    object Submit: ProductEvent()
}
