package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.mvp.order.IOrderContract
import com.toshkadeveloper.sweetshop.mvp.profile.IProfileContract

interface IOrderDBTable {
    fun setOrder(order: Order, model: IOrderContract.Model)
    fun getOrderList(model: IProfileContract.Model)
}