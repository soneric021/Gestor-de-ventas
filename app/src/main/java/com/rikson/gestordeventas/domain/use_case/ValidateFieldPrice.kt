package com.rikson.gestordeventas.domain.use_case

class ValidateFieldPrice {
    fun execute(amount:Double):ValidationResult{
        if (amount == 0.0){
            return ValidationResult(
                success = false,
                errorMessage = "Debe ingresar un precio"
            )
        }
        return ValidationResult(true)
    }
}