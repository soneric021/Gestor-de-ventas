package com.rikson.gestordeventas.domain.use_case

data class InvoiceUseCase(
    val getInvoicesUseCase: GetInvoicesUseCase,
    val deleteInvoicesUseCase: DeleteInvoicesUseCase,
    val saveInvoiceUseCase: SaveInvoiceUseCase,
    val getInvoiceByIdUseCase: GetInvoiceByIdUseCase
)