package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription

class ProductDescriptionPresenter(
    private val keyProduct: String
) : PresenterBase<IProductDescriptionContract.View>(),
    IProductDescriptionContract.Presenter {

    private val model: IProductDescriptionContract.Model = ProductDescriptionModel(this)

    override fun viewIsReady() {
        model.getProductInformation(keyProduct)
    }

    override fun setProductInformation(product: ProductDescription) {
        getView().showProductInformation(product)
    }
}