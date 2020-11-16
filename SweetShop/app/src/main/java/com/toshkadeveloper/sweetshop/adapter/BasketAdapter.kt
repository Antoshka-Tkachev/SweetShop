package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.IBasketAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.ProductInBasketHolder
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket

class BasketAdapter(
    private val context: Context,
    private val products: MutableList<ProductInBasket>
) : RecyclerView.Adapter<ProductInBasketHolder>(),
    IBasketAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInBasketHolder {
        return ProductInBasketHolder(LayoutInflater.from(context).inflate(R.layout.item_product_in_basket, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductInBasketHolder, position: Int) {
        holder.bind(position, products[position])
    }

}