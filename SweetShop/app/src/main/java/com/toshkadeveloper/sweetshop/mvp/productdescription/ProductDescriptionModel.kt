package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductDescriptionDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDescriptionDBTable

class ProductDescriptionModel(
    private val presenter: IProductDescriptionContract.Presenter
) : IProductDescriptionContract.Model {
    private val productDescriptionTable: IProductDescriptionDBTable = ProductDescriptionDBTable()

    override fun getProductInformation(keyProduct: String) {
        productDescriptionTable.getProducts(keyProduct, this)
    }

    override fun getProductInformationResult(product: ProductDescription) {
        presenter.setProductInformation(product)
    }
}