package com.rikson.gestordeventas.domain.use_case

data class ValidationResult(
    val success:Boolean,
    val errorMessage:String? = null
)
