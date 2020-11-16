package com.toshkadeveloper.sweetshop.logic.callback

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

interface ICatalogPaginatorCallback {
    fun productPageRequestCompleted(products: MutableList<Product>, _productLastInformation: String, count: Int)
    fun productPageRequestCancelled()
    fun productFirstPageRequestCompleted(products: MutableList<Product>, _productLastInformation: String, count: Int)
    fun productFirstPageRequestCancelled()
    fun pageRequest()
    fun getProductLastInformation(): String
    fun scrollToCategory(products: MutableList<Product>, _productLastInformation: String, count: Int, category: ProductCategory)
}