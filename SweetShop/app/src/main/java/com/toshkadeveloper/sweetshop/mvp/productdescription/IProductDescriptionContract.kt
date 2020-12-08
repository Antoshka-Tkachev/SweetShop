package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket

interface IProductDescriptionContract {

    interface View : MvpView {
        fun showProductInformation(product: ProductDescription)
        fun setBasketPanel()
        fun showToastAddInBasket()
        fun showToastAddInFavorites()
    }

    interface Presenter : MvpPresenter<View> {
        fun setProductInformation(product: ProductDescription)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
        fun setProduct(_product: Product)
        fun setProductInBasket(product: ProductInBasket)
        fun getProduct(): Product
        fun getProductInBasket(): ProductInBasket
        fun isProductInBasket(): Boolean
        fun addProductInFavorites(product: Product)
        fun addProductInFavoritesResult()
    }

    interface Model {
        fun getProductInformation(keyProduct: String)
        fun getProductInformationResult(product: ProductDescription)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
        fun addProductInFavorites(product: Product)
        fun addProductInFavoritesResult()
    }

}