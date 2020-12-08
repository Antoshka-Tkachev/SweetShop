package com.toshkadeveloper.sweetshop.mvp.profile

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity

interface IProfileContract {

    interface View : MvpView {
        fun updateHistoryAdapter(orders: MutableList<Order>)
        fun showLoader(isShow: Boolean)
        fun getHomeActivity(): HomeActivity
    }

    interface Presenter : MvpPresenter<View> {
        fun updateHistoryAdapter(orders: MutableList<Order>)
    }

    interface Model {
        fun getOrdersList()
        fun getOrdersListResult(orders: MutableList<Order>)
    }

}