package com.rikson.gestordeventas.domain.use_case

class ValidateField {
    fun execute(str:String, input:String): ValidationResult {
        if(str.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Debe ingresar $input"
            )
        }
        return ValidationResult(success = true)
    }
}