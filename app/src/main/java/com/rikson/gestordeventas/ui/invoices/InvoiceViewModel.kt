package com.rikson.gestordeventas.ui.invoices

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rikson.gestordeventas.data.entities.InvoiceWithClientAndCompany
import com.rikson.gestordeventas.domain.use_case.GetInvoiceByIdUseCase
import com.rikson.gestordeventas.domain.use_case.GetInvoicesUseCase

class InvoiceViewModel(private val getInvoicesUseCase: GetInvoicesUseCase, private val getInvoiceByIdUseCase: GetInvoiceByIdUseCase) : ViewModel() {
    val invoicesLivedata:LiveData<List<InvoiceWithClientAndCompany>> by lazy {
        getInvoices()
    }

    private fun getInvoices():LiveData<List<InvoiceWithClientAndCompany>>{
        return getInvoicesUseCase.invoke().asLiveData()
    }

    fun getInvoiceById(id:Int):LiveData<InvoiceWithClientAndCompany>{
        return getInvoiceByIdUseCase.invoke(id).asLiveData()
    }
}