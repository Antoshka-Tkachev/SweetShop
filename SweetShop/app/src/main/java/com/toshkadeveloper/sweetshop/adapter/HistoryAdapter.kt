package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.IHistoryAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.OrderHistoryHolder
import com.toshkadeveloper.sweetshop.logic.data.Order

class HistoryAdapter(
    private val context: Context,
    private val orders: MutableList<Order>
) : RecyclerView.Adapter<OrderHistoryHolder>(),
    IHistoryAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryHolder {
        return OrderHistoryHolder(LayoutInflater.from(context).inflate(R.layout.item_order_in_history, parent, false))
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderHistoryHolder, position: Int) {
        holder.bind(position, orders[position])
    }

}