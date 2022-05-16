package com.rikson.gestordeventas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rikson.gestordeventas.data.entities.ClientEntity
import com.rikson.gestordeventas.data.entities.CompanyEntity
import com.rikson.gestordeventas.data.entities.InvoiceEntity
import com.rikson.gestordeventas.data.entities.ProductEntity

@Database(entities = [InvoiceEntity::class, ClientEntity::class, CompanyEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun invoiceDao():InvoiceDao

    companion object{
        const val DATABASE_NAME = "invoice_manager.db"
    }
}