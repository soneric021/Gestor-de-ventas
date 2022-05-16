package com.rikson.gestordeventas.ui.invoices

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.databinding.FragmentInvoicesBinding
import com.rikson.gestordeventas.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class InvoicesFragment : BaseFragment<FragmentInvoicesBinding>(FragmentInvoicesBinding::inflate) {

    private val model:InvoiceViewModel by inject()
    private lateinit var invoiceAdapter: InvoicesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invoiceAdapter = InvoicesAdapter()
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.delete, menu)
    }
    private fun showAlert(){
        MaterialAlertDialogBuilder(requireContext()).
        setMessage(getString(R.string.are_you_sure_to_delete))
            .setNegativeButton(getString(R.string.cancel)){ _, _ ->

            }.setPositiveButton(getString(R.string.accept)){ _, _ ->
               model.deleteAll()
            }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            showAlert()
            return true
        }
        return super.onOptionsItemSelected(item)
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