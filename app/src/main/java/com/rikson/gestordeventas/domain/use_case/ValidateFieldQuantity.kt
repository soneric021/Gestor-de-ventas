package com.rikson.gestordeventas.domain.use_case

class ValidateFieldQuantity {
    fun execute(quantity:Int): ValidationResult {
        if(quantity < 1 || quantity > 100) {
            return ValidationResult(
                success = false,
                errorMessage = "Debe ingresar una cantidad mayor a 1 y menor a 100"
            )
        }
        return ValidationResult(success = true)
    }
}