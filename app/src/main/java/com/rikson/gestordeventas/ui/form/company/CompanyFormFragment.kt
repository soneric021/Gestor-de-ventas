package com.rikson.gestordeventas.ui.form.company

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.rikson.gestordeventas.databinding.FragmentCompanyFormBinding
import com.rikson.gestordeventas.domain.model.InvoiceEvent
import com.rikson.gestordeventas.ui.base.BaseFragment
import com.rikson.gestordeventas.ui.form.FormViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CompanyFormFragment : BaseFragment<FragmentCompanyFormBinding>(FragmentCompanyFormBinding::inflate) {

    private val model by sharedViewModel<FormViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        model.state.observe(viewLifecycleOwner){
            if(it.companyNameError.isNotEmpty()) {
                binding.etBussinessName.isErrorEnabled = true
                binding.etBussinessName.error = it.companyNameError
            }
            if(it.companyRifError.isNotEmpty()) {
                binding.etRif.isErrorEnabled = true
                binding.etRif.error = it.companyRifError
            }

        }
    }

    private fun setupListeners() {
        binding.etBussinessName.editText?.doOnTextChanged { text,_,_,_ ->
            if(binding.etBussinessName.isErrorEnabled) binding.etBussinessName.isErrorEnabled = false
            model.onEvent(InvoiceEvent.CompanyNameChanged(text?.toString() ?: ""))
        }
        binding.etRif.editText?.doOnTextChanged { text,_,_,_ ->
            if(binding.etRif.isErrorEnabled) binding.etRif.isErrorEnabled = false
            model.onEvent(InvoiceEvent.CompanyRifChanged(text?.toString() ?: ""))
        }
    }
}