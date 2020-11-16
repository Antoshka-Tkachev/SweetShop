package com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogModel
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract

class SearchCatalogModel(presenter: IBaseCatalogContract.Presenter) : BaseCatalogModel(presenter) {
    override fun getProductByName(productName: String) {
        productDBTable.getProductByName(productName, this)
    }

    override fun getProductByNameResult(products: MutableList<Product>) {
        presenter.setCatalogAndCategoriesData(_products = products)
    }
}