package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CatalogItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(position: Int, item: Any = 0)

    companion object {
        fun inflateView(context: Context, idResource: Int, parent: ViewGroup) : View {
            return LayoutInflater.from(context).inflate(idResource, parent, false)
        }
    }
}