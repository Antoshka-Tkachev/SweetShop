package com.toshkadeveloper.sweetshop.mvp.basket

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity

interface IBasketContract {
    interface View : MvpView {
        fun updateAdapter(products: MutableList<ProductInBasket>)
        fun showLoader(isShow: Boolean)
        fun getHomeActivity(): HomeActivity
        fun updateOrderPanel(price: Int, count: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun updateAdapter(products: MutableList<ProductInBasket>)
    }

    interface Model {
        fun getProductsInBasket()
        fun getProductsInBasketResult(products: MutableList<ProductInBasket>)
    }

}