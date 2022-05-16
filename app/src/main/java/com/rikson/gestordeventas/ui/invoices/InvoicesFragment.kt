package com.rikson.gestordeventas.ui.invoices

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikson.gestordeventas.databinding.FragmentInvoicesBinding
import com.rikson.gestordeventas.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class InvoicesFragment : BaseFragment<FragmentInvoicesBinding>(FragmentInvoicesBinding::inflate) {

    private val model:InvoiceViewModel by inject()
    private lateinit var invoiceAdapter: InvoicesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invoiceAdapter = InvoicesAdapter()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvInvoices.apply {
            adapter = invoiceAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        model.invoicesLivedata.observe(viewLifecycleOwner){
            invoiceAdapter.submitList(it)
        }
    }
}