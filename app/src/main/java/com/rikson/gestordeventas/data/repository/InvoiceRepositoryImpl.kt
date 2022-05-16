package com.rikson.gestordeventas.data.repository

import com.rikson.gestordeventas.data.entities.*
import com.rikson.gestordeventas.data.local.InvoiceDao
import com.rikson.gestordeventas.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.Flow

class InvoiceRepositoryImpl(private val invoiceDao: InvoiceDao) : InvoiceRepository {
    override fun getInvoices(): Flow<List<InvoiceWithClientAndCompany>> {
       return invoiceDao.getInvoices()
    }

    override fun getInvoiceById(id: Int): Flow<InvoiceWithClientAndCompany> {
       return invoiceDao.getInvoiceById(id)
    }

    override suspend fun saveInvoice(
        clientEntity: ClientEntity,
        companyEntity: CompanyEntity,
        products: List<ProductEntity>
    ) : Int {
        return invoiceDao.saveInvoice(companyEntity, clientEntity, products)
    }

}