package com.rikson.gestordeventas.ui.form.product

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikson.gestordeventas.databinding.FragmentProductFormBinding
import com.rikson.gestordeventas.domain.model.ProductEvent
import com.rikson.gestordeventas.ui.base.BaseFragment
import com.rikson.gestordeventas.ui.form.FormViewModel
import com.rikson.gestordeventas.utils.addAutomaticThousandSeparator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductFormFragment : BaseFragment<FragmentProductFormBinding>(FragmentProductFormBinding::inflate) {

    private val model by sharedViewModel<FormViewModel>()
    private lateinit var  productsAdapter:ProductsAdapter
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       productsAdapter = ProductsAdapter()
       setupListeners()
       setupObservers()
       setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun clearEditables(){
        binding.etDescription.editText?.text?.clear()
        binding.etCode.editText?.text?.clear()
        binding.etPrice.editText?.text?.clear()
    }
    private fun setupObservers() {
        model.state.observe(viewLifecycleOwner){
            productsAdapter.submitList(it.invoiceEntity.products)
            if(it.codeError.isNotEmpty()) {
                binding.etCode.isErrorEnabled = true
                binding.etCode.error = it.codeError
            }
            if(it.descriptionError.isNotEmpty()) {
                binding.etDescription.isErrorEnabled = true
                binding.etDescription.error = it.descriptionError
            }
            if(it.priceError.isNotEmpty()) {
                binding.etPrice.isErrorEnabled = true
                binding.etPrice.error = it.priceError
            }
            binding.tvSubtotal.text = it.invoiceEntity.total.addAutomaticThousandSeparator()
        }

        model.productAdded.observe(viewLifecycleOwner){
            if(it){
                clearEditables()
                model.isProductAdded()
            }
        }

        model.messageQuantity.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { message ->
                showToast(message)
            }
        }
    }

    private fun setupListeners() {
        binding.etCode.editText?.doOnTextChanged { text, _, _, _ ->
            if(binding.etCode.isErrorEnabled) binding.etCode.isErrorEnabled = false
            model.onProductEvent(ProductEvent.CodeChanged(text?.toString() ?: ""))
        }
        binding.etDescription.editText?.doOnTextChanged { text, _, _, _ ->
            if(binding.etDescription.isErrorEnabled) binding.etDescription.isErrorEnabled = false
            model.onProductEvent(ProductEvent.DescriptionChanged(text?.toString() ?: ""))
        }
        binding.etPrice.editText?.doOnTextChanged { text,  _, _, _  ->
            if(binding.etPrice.isErrorEnabled) binding.etPrice.isErrorEnabled = false
            model.onProductEvent(ProductEvent.PriceChanged(text?.toString()?.toDoubleOrNull() ?: 0.0))
        }
        binding.etQuantity.doOnTextChanged { text,  _, _, _  ->
            model.onProductEvent(ProductEvent.QuantityChanged(text?.toString()?.toInt() ?: 0))
        }
        binding.btnPlus.setOnClickListener {
            binding.etQuantity.apply {
                var quantity = text.toString().toInt()
                quantity++
                setText(quantity.toString())
            }
        }
        binding.btnMinus.setOnClickListener {
            binding.etQuantity.apply {
                var quantity = text.toString().toInt()
                quantity--
                setText(quantity.toString())
            }
        }
        binding.btnAddToCart.setOnClickListener {
            model.onProductEvent(ProductEvent.Submit)
        }
    }
}