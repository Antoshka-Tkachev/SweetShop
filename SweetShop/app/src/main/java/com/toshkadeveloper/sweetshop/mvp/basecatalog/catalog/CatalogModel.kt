package com.toshkadeveloper.sweetshop.mvp.basecatalog.catalog

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogModel
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract

class CatalogModel(presenter: IBaseCatalogContract.Presenter) : BaseCatalogModel(presenter) {

    override fun getProductByName(productName: String) {
        TODO("Not yet implemented")
    }

    override fun getProductByNameResult(products: MutableList<Product>) {
        TODO("Not yet implemented")
    }
}