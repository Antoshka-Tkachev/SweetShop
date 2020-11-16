package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ISearchCategoriesAdapter
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class SearchCategoryHolder (
    itemView: View,
    private val catalogAdapter: ICatalogAdapter,
    private val categoriesAdapter: ISearchCategoriesAdapter
) : RecyclerView.ViewHolder(itemView) {

    private var tv_nameCategory: TextView = itemView.findViewById(R.id.tv_nameCategory_holder)

    fun bind(position: Int, name: String, productsCount: Int) {
        tv_nameCategory.text = name
        itemView.setOnClickListener {
            val positionCategoryHolder = catalogAdapter.getCatalog().indexOf(
                ProductCategory(
                    name
                )
            )
            catalogAdapter.scrollToPosition(positionCategoryHolder)
        }
    }

}