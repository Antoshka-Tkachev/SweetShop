package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

interface IProductDBTable {
    fun getProductPage(productLastInformation: String, productCount: Int, callback: ICatalogPaginatorCallback)
    fun getProductFirstPage(productCount: Int, callback: ICatalogPaginatorCallback)
    fun requestForScrollToCategory(productLastInformation: String, productCount: Int, callback: ICatalogPaginatorCallback, category: ProductCategory)
    fun getProductByName(name: String, model: IBaseCatalogContract.Model)
}