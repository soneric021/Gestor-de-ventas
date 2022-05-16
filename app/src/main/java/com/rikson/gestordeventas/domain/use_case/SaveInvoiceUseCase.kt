package com.rikson.gestordeventas.domain.use_case

import com.rikson.gestordeventas.data.entities.*
import com.rikson.gestordeventas.domain.repository.InvoiceRepository
import kotlin.jvm.Throws

class SaveInvoiceUseCase(private val repository: InvoiceRepository) {

    @Throws(InvalidInvoiceException::class)
    suspend operator fun invoke( clientEntity: ClientEntity, companyEntity: CompanyEntity, products:List<ProductEntity>):Int{
        return repository.saveInvoice(clientEntity, companyEntity, products)
    }
}