package com.rikson.gestordeventas.ui.invoices

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rikson.gestordeventas.data.entities.InvoiceWithClientAndCompany
import com.rikson.gestordeventas.domain.use_case.GetInvoiceByIdUseCase
import com.rikson.gestordeventas.domain.use_case.GetInvoicesUseCase
import com.rikson.gestordeventas.domain.use_case.InvoiceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InvoiceViewModel(private val invoiceUseCase: InvoiceUseCase) : ViewModel() {
    val invoicesLivedata:LiveData<List<InvoiceWithClientAndCompany>> by lazy {
        getInvoices()
    }

    private fun getInvoices():LiveData<List<InvoiceWithClientAndCompany>>{
        return invoiceUseCase.getInvoicesUseCase.invoke().asLiveData()
    }

    fun getInvoiceById(id:Int):LiveData<InvoiceWithClientAndCompany>{
        return invoiceUseCase.getInvoiceByIdUseCase.invoke(id).asLiveData()
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            invoiceUseCase.deleteInvoicesUseCase.invoke()
        }
    }
}