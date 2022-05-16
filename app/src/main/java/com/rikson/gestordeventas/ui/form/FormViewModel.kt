package com.rikson.gestordeventas.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rikson.gestordeventas.domain.model.*
import com.rikson.gestordeventas.domain.use_case.InvoiceUseCase
import com.rikson.gestordeventas.domain.use_case.Validators
import kotlinx.coroutines.launch

class FormViewModel(private val invoiceUseCase: InvoiceUseCase, private val validators: Validators = Validators()) : ViewModel() {

    private val statusState = MutableLiveData<InvoiceState>()
    val state:LiveData<InvoiceState>
        get() = statusState

    private val statusMessageProduct = MutableLiveData<Event<String>>()
    val messageProduct : LiveData<Event<String>>
        get() = statusMessageProduct

    private val statusMessageQuantity = MutableLiveData<Event<String>>()
    val messageQuantity : LiveData<Event<String>>
        get() = statusMessageQuantity

    private val statusProductAdded = MutableLiveData<Boolean>()
    val productAdded:LiveData<Boolean>
        get() = statusProductAdded

    init {
        statusState.value = InvoiceState()
    }

    fun isProductAdded(){
        statusProductAdded.value = false
    }
    fun onProductEvent(event: ProductEvent){
        when(event){
            is ProductEvent.CodeChanged -> {
                statusState.value?.productEntity = statusState.value?.productEntity?.copy(code = event.code)!!
                statusState.value = state.value?.copy(codeError = "")
            }
            is ProductEvent.DescriptionChanged -> {
                statusState.value?.productEntity = statusState.value?.productEntity?.copy(description = event.description)!!
                statusState.value = state.value?.copy(descriptionError = "")
            }
            is ProductEvent.PriceChanged -> {
                statusState.value?.productEntity = statusState.value?.productEntity?.copy(price = event.price)!!
                statusState.value = statusState.value?.copy(priceError = "")
            }
            is ProductEvent.QuantityChanged -> {
                statusState.value?.productEntity = statusState.value?.productEntity?.copy(quantity = event.quantity)!!
            }
            ProductEvent.Submit -> {
                addProduct()
            }
        }
    }

    private fun addProduct(){
        val resultCode = validators.validateField.execute(state.value?.productEntity?.code ?: "", "un Codigo")
        val resultPrice = validators.validateFieldPrice.execute(state.value?.productEntity?.price ?: 0.0)
        val resultDescription = validators.validateField.execute(state.value?.productEntity?.description ?: "", "una descripcion")
        val resultQuantity = validators.validateFieldQuantity.execute(state.value?.productEntity?.quantity ?: 0)

        val hasError = listOf(
            resultCode,
            resultPrice,
            resultDescription,
            resultQuantity
        ).any { !it.success }

        if (!hasError){
            statusState.value?.productEntity?.let {
                statusState.value?.invoiceEntity?.products?.add(it)
            }
            statusState.value?.productEntity =
                statusState.value?.productEntity?.
                copy(code = "", description = "", price = 0.0)!!
            val total = statusState.value?.invoiceEntity?.products?.sumOf { it.price * it.quantity }!!
            statusState.value?.invoiceEntity = statusState.value?.invoiceEntity?.copy(total = total)!!
            statusProductAdded.value = true
        }else{
            statusState.value = statusState.value?.copy(
                priceError = resultPrice.errorMessage ?: "",
                codeError = resultCode.errorMessage ?: "",
                descriptionError = resultDescription.errorMessage ?: ""
            )
            if(!resultQuantity.success){
                statusMessageQuantity.value = Event(resultQuantity.errorMessage ?: "")
            }
        }
    }

    fun onEvent(event:InvoiceEvent){
        when(event){
            is InvoiceEvent.NameChanged -> {
                statusState.value?.invoiceEntity?.client =
                    statusState.value?.invoiceEntity?.client?.copy(name = event.name)!!
                statusState.value = statusState.value?.copy(nameError = "")
            }
            is InvoiceEvent.LastNameChanged -> {
                statusState.value?.invoiceEntity?.client =
                    statusState.value?.invoiceEntity?.client?.copy(lastName = event.lastName)!!
                statusState.value = statusState.value?.copy(lastNameError = "")
            }
            is InvoiceEvent.IdentifierChanged -> {
                statusState.value?.invoiceEntity?.client =
                    statusState.value?.invoiceEntity?.client?.copy(identifier = event.identifier)!!
                statusState.value = statusState.value?.copy(identifierError = "")
            }
            is InvoiceEvent.CompanyNameChanged -> {
                statusState.value?.invoiceEntity?.company =
                    statusState.value?.invoiceEntity?.company?.copy(bussinessName = event.companyName)!!
                statusState.value = statusState.value?.copy(companyNameError = "")
            }
            is InvoiceEvent.CompanyRifChanged -> {
                statusState.value?.invoiceEntity?.company =
                    statusState.value?.invoiceEntity?.company?.copy(rif = event.companyRif)!!
                statusState.value = statusState.value?.copy(companyRifError = "")
            }
            InvoiceEvent.Submit -> {
                val isProductEmpty = statusState.value?.invoiceEntity?.products?.isEmpty()
                if(isProductEmpty == true){
                    statusMessageProduct.value = Event("Debe tener al menos un producto")
                }else{
                    saveInvoice()
                }
            }
        }
    }

    fun next(formStep: Int){
        when(formStep){
            FormSteps.Client.ordinal -> {
                val resultName = validators.validateField.execute(statusState.value?.invoiceEntity?.client?.name ?: "", "Un Nombre")
                val resultLastName = validators.validateField.execute(statusState.value?.invoiceEntity?.client?.lastName ?: "", "Un apellido")
                val resultIdentifier = validators.validateField.execute(statusState.value?.invoiceEntity?.client?.identifier ?: "", "Una Cedula")

                val hasError = listOf(
                    resultName,
                    resultLastName,
                    resultIdentifier
                ).any { !it.success }

                if(hasError){
                    statusState.value = statusState.value?.copy(
                        nameError = resultName.errorMessage ?: "",
                        lastNameError = resultLastName.errorMessage ?: "",
                        identifierError = resultIdentifier.errorMessage ?: ""
                    )
                }else{
                    statusState.value = statusState.value?.copy(canContinue = true)
                }
            }
            FormSteps.Company.ordinal -> {
                val resultCompanyName = validators.validateField.execute(statusState.value?.invoiceEntity?.company?.bussinessName ?: "", "Una compañia")
                val resultCompanyRif = validators.validateField.execute(statusState.value?.invoiceEntity?.company?.rif ?: "", "Un Rif")

                val hasError = listOf(
                    resultCompanyName,
                    resultCompanyRif
                ).any { !it.success }

                if(hasError){
                    statusState.value = statusState.value?.copy(
                        companyNameError = resultCompanyName.errorMessage ?: "",
                        companyRifError = resultCompanyRif.errorMessage ?: ""
                    )
                }else{
                    statusState.value = statusState.value?.copy(canContinue = true)
                }
            }
        }
    }

    fun clearState(){
        statusState.value = InvoiceState()
    }

    private fun saveInvoice(){
        viewModelScope.launch {
           val invoiceId = invoiceUseCase.saveInvoiceUseCase.invoke(
               clientEntity = statusState.value?.invoiceEntity?.client!!,
               companyEntity = statusState.value?.invoiceEntity?.company!!,
               products = statusState.value?.invoiceEntity?.products?.toList()!!
            )
            statusState.value = statusState.value?.copy(invoiceId = invoiceId)!!
        }

    }

}