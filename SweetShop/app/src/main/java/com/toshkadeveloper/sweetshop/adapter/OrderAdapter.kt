package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.IOrderAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.ProductInOrderHolder
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket

class OrderAdapter(
    private val context: Context,
    private val products: MutableList<ProductInBasket>
) : RecyclerView.Adapter<ProductInOrderHolder>(),
    IOrderAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInOrderHolder {
        return ProductInOrderHolder(LayoutInflater.from(context).inflate(R.layout.item_product_in_order, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductInOrderHolder, position: Int) {
        holder.bind(position, products[position])
    }

}