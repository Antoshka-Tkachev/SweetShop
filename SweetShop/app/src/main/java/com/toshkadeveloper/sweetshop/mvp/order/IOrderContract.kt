package com.toshkadeveloper.sweetshop.mvp.order

import android.app.Dialog
import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.logic.data.User

interface IOrderContract {

    interface View : MvpView {
        fun showLoader(isShow: Boolean)
        fun onCreateDialog(orderNumber: String): Dialog
        fun updateAdapter(products: MutableList<ProductInBasket>)
        fun updateInfoAboutProduct(price: Int, count: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun onClickOrdering(order: Order)
        fun setUser(_user: User)
        fun updateAdapter(_products: MutableList<ProductInBasket>)
        fun getCount(): Int
        fun getPrice(): Int
        fun getProducts(): MutableList<ProductInBasket>
        fun orderIsProcessed(orderId: String)
    }

    interface Model {
        fun setOrder(order: Order)
        fun setOrderResult(orderId: String)
        fun getProductsInBasket()
        fun getProductsInBasketResult(products: MutableList<ProductInBasket>)
    }
}