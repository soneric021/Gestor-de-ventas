package com.rikson.gestordeventas.domain.use_case

data class Validators(
    val validateField: ValidateField = ValidateField(),
    val validateFieldQuantity: ValidateFieldQuantity = ValidateFieldQuantity(),
    val validateFieldPrice: ValidateFieldPrice = ValidateFieldPrice()
)
