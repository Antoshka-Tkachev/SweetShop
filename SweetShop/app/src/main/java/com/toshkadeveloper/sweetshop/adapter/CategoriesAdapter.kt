package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.CategoryHolder
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback

class CategoriesAdapter(
    private val context: Context,
    private val names: MutableList<String> = mutableListOf(),
    private val productsCount: MutableList<Int> = mutableListOf(),
    private val catalogAdapter: ICatalogAdapter,
    private val catalogPaginator: ICatalogPaginatorCallback
) : RecyclerView.Adapter<CategoryHolder>(),
    ICategoriesAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder (
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false),
            catalogAdapter,
            this,
            catalogPaginator
        )
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(position, names[position], productsCount[position])
    }

    override fun calculateCategoryPosition(position: Int): Int {
        var newPosition = -1
        for (i in 0 until position) {
            newPosition++
            newPosition += productsCount[i]
        }
        newPosition++
        return newPosition
    }
}