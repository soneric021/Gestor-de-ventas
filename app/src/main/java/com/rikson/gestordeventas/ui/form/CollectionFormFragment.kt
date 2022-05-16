package com.rikson.gestordeventas.ui.form

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.databinding.FragmentCollectionFormBinding
import com.rikson.gestordeventas.domain.model.InvoiceEvent
import com.rikson.gestordeventas.ui.base.BaseFragment
import com.rikson.gestordeventas.utils.Constants
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CollectionFormFragment : BaseFragment<FragmentCollectionFormBinding>(FragmentCollectionFormBinding::inflate) {

    private val model by sharedViewModel<FormViewModel>()
    private lateinit var adapter:CollectionFormAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CollectionFormAdapter(this)
        binding.viewpager.adapter = adapter
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(binding.viewpager.currentItem == 0){
                showAlert()
            }
        }
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        model.state.observe(viewLifecycleOwner){
            if(it.invoiceId != 0){
                val bundle = bundleOf(Constants.INVOICEID to it.invoiceId)
                model.clearState()
                findNavController().navigate(R.id.action_to_invoice, bundle)
            }
        }
        model.messageProduct.observe(viewLifecycleOwner){
           it.getContentIfNotHandled()?.let { message ->
               showToast(message)
           }
        }
    }
    private fun onPageChange(position:Int){
        when(position){
            FormSteps.Client.ordinal ->{
                binding.btnNext.text = getString(R.string.next)
                binding.btnPrevious.text = getString(R.string.finish)
            }
            FormSteps.Company.ordinal ->{
                binding.btnNext.text = getString(R.string.next)
                binding.btnPrevious.text = getString(R.string.prev)
            }
            FormSteps.Products.ordinal ->{
                binding.btnNext.text = getString(R.string.totalize)
                binding.btnPrevious.text =getString(R.string.prev)
            }
        }
    }
    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            if(binding.viewpager.currentItem < adapter.itemCount - 1) {
                model.next(binding.viewpager.currentItem)
                if(model.state.value?.canContinue == true ){
                    binding.viewpager.currentItem++
                    model.state.value?.canContinue = false
                }
            }else {
                model.onEvent(InvoiceEvent.Submit)
            }
            onPageChange(binding.viewpager.currentItem)
    }

        binding.btnPrevious.setOnClickListener {
            if(binding.viewpager.currentItem == 0) {
                showAlert()
            }else {
                binding.viewpager.currentItem--
            }
            onPageChange(binding.viewpager.currentItem)
        }
    }

    private fun showAlert() {
        MaterialAlertDialogBuilder(requireContext()).
            setMessage(getString(R.string.are_you_sure_to_close))
            .setNegativeButton(getString(R.string.cancel)){ _, _ ->

            }.setPositiveButton(getString(R.string.accept)){ _, _ ->
                    findNavController().navigateUp()
        }.show()
    }

}