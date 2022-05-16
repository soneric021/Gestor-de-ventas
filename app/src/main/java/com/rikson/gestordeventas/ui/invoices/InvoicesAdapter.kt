package com.rikson.gestordeventas.ui.invoices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.data.entities.InvoiceWithClientAndCompany
import com.rikson.gestordeventas.databinding.ItemInvoicesBinding
import com.rikson.gestordeventas.utils.addAutomaticThousandSeparator

class InvoicesAdapter : RecyclerView.Adapter<InvoicesAdapter.ViewHolder>() {

    class ViewHolder(private val binding:ItemInvoicesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:InvoiceWithClientAndCompany){
            val context = binding.root.context
            binding.tvBussiness.text = item.companyEntity.bussinessName
            binding.tvClient.text = context.getString(R.string.client_name_lastName, item.clientEntity.name, item.clientEntity.lastName)
            binding.tvAmount.text = context.getString(R.string.amount_total_value, item.invoiceEntity.total.addAutomaticThousandSeparator())
            binding.tvCountItems.text = context.getString(R.string.items_quantity, item.products.size.toString())
        }
    }
    private var data:List<InvoiceWithClientAndCompany> = emptyList()
    fun submitList(list: List<InvoiceWithClientAndCompany>){
        data = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemInvoicesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}