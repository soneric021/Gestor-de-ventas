package com.rikson.gestordeventas.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rikson.gestordeventas.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM tb_invoice")
    fun getInvoices():Flow<List<InvoiceWithClientAndCompany>>

    @Query("SELECT * FROM tb_invoice where :id = id")
    fun getInvoiceById(id:Int):Flow<InvoiceWithClientAndCompany>

    @Transaction
    suspend fun saveInvoice(companyEntity: CompanyEntity, clientEntity: ClientEntity, products:List<ProductEntity>):Int{

        val clientId = insert(clientEntity).toInt()
        val companyId = insert(companyEntity).toInt()
        val invoice = InvoiceEntity(total = products.sumOf { product ->  product.price * product.quantity })
        invoice.clientId = clientId
        invoice.companyId = companyId

        val invoiceId = insert(invoice).toInt()

        products.map { product ->
            product.invoiceId = invoiceId
            insert(product)
        }
        return invoiceId
    }
    @Insert
    fun insert(companyEntity: CompanyEntity):Long

    @Insert
    fun insert(clientEntity: ClientEntity):Long

    @Insert
    fun insert(product:ProductEntity)

    @Insert
    fun insert(invoiceEntity: InvoiceEntity):Long

    @Delete
    fun deleteAll(list: List<InvoiceEntity>)
}