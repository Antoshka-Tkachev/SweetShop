package com.toshkadeveloper.sweetshop.mvp.profile

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.Order

class ProfilePresenter : PresenterBase<IProfileContract.View>(), IProfileContract.Presenter {

    private val model: IProfileContract.Model = ProfileModel(this)

    override fun viewIsReady() {
        getView().showLoader(true)
        model.getOrdersList()
    }

    override fun updateHistoryAdapter(orders: MutableList<Order>) {
        getView().showLoader(false)
        getView().updateHistoryAdapter(orders)
    }

}