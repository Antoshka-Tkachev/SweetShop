package com.toshkadeveloper.sweetshop.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.viewholder.CatalogItemHolder
import com.toshkadeveloper.sweetshop.adapter.viewholder.CatalogProductHolder
import com.toshkadeveloper.sweetshop.adapter.viewholder.CategoryHeaderHolder
import com.toshkadeveloper.sweetshop.adapter.viewholder.CategoryTopHeaderHolder
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract


class CatalogAdapter(
    private val context: Context,
    private val layoutManager: GridLayoutManager,
    private val callbackFragment: IBaseCatalogContract.View
) :
    RecyclerView.Adapter<CatalogItemHolder>(),
    ICatalogAdapter {

    private val TOP_HEADER: Int = 0
    private val HEADER: Int = 1
    private val PRODUCT: Int = 2
    private val catalog = mutableListOf<Any>()


    override fun getCatalog(): MutableList<Any> {
        return catalog
    }

    override fun getItemCount(): Int {
        return catalog.size
    }

    override fun updateAdapterData() {
        super.notifyDataSetChanged()
    }

    override fun scrollToPosition(position: Int) {
        smoothScroller.targetPosition = position
        layoutManager.startSmoothScroll(smoothScroller)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return TOP_HEADER
        return when {
            catalog[position] is ProductCategory -> HEADER
            catalog[position] is Product -> PRODUCT
            else -> throw Exception("getItemViewType: NoneType")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemHolder {
        return when (viewType) {
            TOP_HEADER -> CategoryTopHeaderHolder(
                CatalogItemHolder.inflateView(
                    context,
                    R.layout.item_category_top_header,
                    parent
                )
            )
            HEADER -> CategoryHeaderHolder(
                CatalogItemHolder.inflateView(
                    context,
                    R.layout.item_category_header,
                    parent
                )
            )
            PRODUCT -> CatalogProductHolder(
                CatalogItemHolder.inflateView(
                    context,
                    R.layout.item_catalog_product,
                    parent
                ),
                callbackFragment
            )
            else -> throw Exception("None ViewHolder type")
        }
    }

    override fun onBindViewHolder(holder: CatalogItemHolder, position: Int) {
        holder.bind(position, catalog[position])
    }

    private val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }

}