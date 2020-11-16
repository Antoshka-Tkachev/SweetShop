package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ISearchCategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.SearchCategoryHolder

class SearchCategoriesAdapter (
    private val context: Context,
    private val names: MutableList<String> = mutableListOf(),
    private val productsCount: MutableList<Int> = mutableListOf(),
    private val catalogAdapter: ICatalogAdapter
) : RecyclerView.Adapter<SearchCategoryHolder>(),
    ISearchCategoriesAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCategoryHolder {
        return SearchCategoryHolder (
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false),
            catalogAdapter,
            this
        )
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: SearchCategoryHolder, position: Int) {
        holder.bind(position, names[position], productsCount[position])
    }

}