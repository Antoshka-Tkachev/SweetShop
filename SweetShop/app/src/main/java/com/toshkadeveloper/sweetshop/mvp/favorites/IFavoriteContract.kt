package com.toshkadeveloper.sweetshop.mvp.favorites

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity

interface IFavoriteContract {

    interface View : MvpView {
        fun updateAdapter(catalog: MutableList<Any>)
        fun showLoader(isShow:Boolean)
        fun getHomeActivity(): HomeActivity
        fun addProductInBasket(product: Product)
        fun showToastAddInBasket()
    }

    interface Presenter : MvpPresenter<View> {
        fun setFavoritesProduct(products: MutableList<Product>)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
    }

    interface Model {
        fun getFavorites()
        fun getFavoritesResult(products: MutableList<Product>)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
    }

}