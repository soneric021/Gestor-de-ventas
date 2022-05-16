package com.rikson.gestordeventas.ui.invoices

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.databinding.FragmentInvoiceBinding
import com.rikson.gestordeventas.ui.base.BaseFragment
import com.rikson.gestordeventas.ui.form.product.ProductsAdapter
import com.rikson.gestordeventas.utils.addAutomaticThousandSeparator
import org.koin.android.ext.android.inject

class InvoiceFragment : BaseFragment<FragmentInvoiceBinding>(FragmentInvoiceBinding::inflate) {

    private val model:InvoiceViewModel by inject()
    private lateinit var productsAdapter: ProductsAdapter
    private var invoiceId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        invoiceId = arguments?.getInt("invoiceId", 0) ?: 0
        productsAdapter = ProductsAdapter()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(R.id.action_to_home)
        }
        setupViews()
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_to_home)
        }
    }

    private fun setupViews() {
        binding.animationCheck.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                binding.animationCheck.visibility = View.GONE
                binding.invoiceGroup.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })

    }

    private fun setupObservers() {
        model.getInvoiceById(invoiceId).observe(viewLifecycleOwner){
            binding.tvClient.text = getString(R.string.client_name_lastName, it.clientEntity.name, it.clientEntity.lastName)
            binding.tvIdentifier.text = getString(R.string.identifier_value, it.clientEntity.identifier)
            binding.tvName.text = it.companyEntity.bussinessName
            binding.tvRif.text = it.companyEntity.rif
            binding.tvSubtotal.text = it.invoiceEntity.total.addAutomaticThousandSeparator()
            productsAdapter.submitList(it.products)
        }
    }

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }
    }
}