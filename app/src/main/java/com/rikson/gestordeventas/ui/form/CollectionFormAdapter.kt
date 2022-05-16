package com.rikson.gestordeventas.ui.form

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rikson.gestordeventas.ui.form.client.ClientFormFragment
import com.rikson.gestordeventas.ui.form.company.CompanyFormFragment
import com.rikson.gestordeventas.ui.form.product.ProductFormFragment

class CollectionFormAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    val listFragment:List<Fragment> = listOf(ClientFormFragment(), CompanyFormFragment(), ProductFormFragment())

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }

}