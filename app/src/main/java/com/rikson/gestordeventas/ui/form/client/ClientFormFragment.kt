package com.rikson.gestordeventas.ui.form.client

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.rikson.gestordeventas.databinding.FragmentClientFormBinding
import com.rikson.gestordeventas.domain.model.InvoiceEvent
import com.rikson.gestordeventas.ui.base.BaseFragment
import com.rikson.gestordeventas.ui.form.FormViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ClientFormFragment : BaseFragment<FragmentClientFormBinding>(FragmentClientFormBinding::inflate) {

    private val model by sharedViewModel<FormViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        model.state.observe(viewLifecycleOwner){
            if(it.nameError.isNotEmpty()) {
                binding.etName.isErrorEnabled = true
                binding.etName.error = it.nameError
            }
            if(it.lastNameError.isNotEmpty()) {
                binding.etLastname.isErrorEnabled = true
                binding.etLastname.error = it.lastNameError
            }
            if(it.identifierError.isNotEmpty()) {
                binding.etIdentifier.isErrorEnabled = true
                binding.etIdentifier.error = it.identifierError
            }
        }
    }

    private fun setupListeners() {
        binding.etName.editText?.doOnTextChanged { text, _,_,_ ->
            if(binding.etName.isErrorEnabled) binding.etName.isErrorEnabled = false
            model.onEvent(InvoiceEvent.NameChanged(text?.toString() ?: ""))
        }
        binding.etLastname.editText?.doOnTextChanged { text, _,_,_ ->
            if(binding.etLastname.isErrorEnabled) binding.etLastname.isErrorEnabled = false
            model.onEvent(InvoiceEvent.LastNameChanged(text?.toString() ?: ""))
        }
        binding.etIdentifier.editText?.doOnTextChanged { text,_,_,_->
            if(binding.etIdentifier.isErrorEnabled) binding.etIdentifier.isErrorEnabled = false
            model.onEvent(InvoiceEvent.IdentifierChanged(text?.toString() ?: ""))
        }
    }

}