package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class CategoryTopHeaderHolder (itemView: View) : CatalogItemHolder(itemView) {

    override fun bind(position: Int, productCategory: Any) {
        if (productCategory is ProductCategory) {
            var categoryName: TextView = itemView.findViewById(R.id.tv_item_category_top_header)
            categoryName.text = productCategory.categoryName
        }
    }

}