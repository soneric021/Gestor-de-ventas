package com.rikson.gestordeventas.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.databinding.FragmentMainBinding
import com.rikson.gestordeventas.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnListInvoice.setOnClickListener {
            findNavController().navigate(R.id.action_to_invoices)
        }

        binding.btnNewInvoice.setOnClickListener {
            findNavController().navigate(R.id.action_to_form)
        }

    }
}