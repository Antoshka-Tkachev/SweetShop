package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.paginator.CatalogPaginator.Companion.PAGE_SIZE
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDBTable
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class CategoryHolder(
    itemView: View,
    private val catalogAdapter: ICatalogAdapter,
    private val categoriesAdapter: ICategoriesAdapter,
    private val catalogPaginator: ICatalogPaginatorCallback
) : RecyclerView.ViewHolder(itemView) {

    private val productDBTable: IProductDBTable = ProductDBTable()
    private var tv_nameCategory: TextView = itemView.findViewById(R.id.tv_nameCategory_holder)

    fun bind(position: Int, name: String, productsCount: Int) {
        tv_nameCategory.text = name

        itemView.setOnClickListener {
            val positionCategoryHolder = categoriesAdapter.calculateCategoryPosition(position)
            if (positionCategoryHolder + PAGE_SIZE < catalogAdapter.getItemCount()) {
                catalogAdapter.scrollToPosition(positionCategoryHolder)
            } else {
                productDBTable.requestForScrollToCategory(
                    catalogPaginator.getProductLastInformation(),
                    positionCategoryHolder + PAGE_SIZE,
                    catalogPaginator,
                    ProductCategory(name)
                )
            }
        }
    }
}