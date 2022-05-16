package com.rikson.gestordeventas.ui.form.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rikson.gestordeventas.R
import com.rikson.gestordeventas.data.entities.ProductEntity
import com.rikson.gestordeventas.databinding.ItemProductsBinding
import com.rikson.gestordeventas.utils.addAutomaticThousandSeparator

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    class ViewHolder(private val binding:ItemProductsBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(product:ProductEntity){
            val context = binding.root.context
            binding.tvCode.text = context.getString(R.string.code_value, product.code)
            binding.tvDescription.text = product.description
            binding.tvQuantity.text = context.getString(R.string.quantity_x_price, product.quantity.toString(), product.price.addAutomaticThousandSeparator())
            binding.tvTotal.text = (product.price * product.quantity).addAutomaticThousandSeparator()
        }
    }
    private var data:List<ProductEntity> = emptyList()

    fun submitList(list:List<ProductEntity>){
        data = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}