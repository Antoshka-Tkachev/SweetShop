package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription

interface IProductDescriptionContract {

    interface View : MvpView {
        fun showProductInformation(product: ProductDescription)
    }

    interface Presenter : MvpPresenter<View> {
        fun setProductInformation(product: ProductDescription)
    }

    interface Model {
        fun getProductInformation(keyProduct: String)
        fun getProductInformationResult(product: ProductDescription)
    }

}