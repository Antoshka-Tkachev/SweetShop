package com.toshkadeveloper.sweetshop.mvp.profile

import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.repository.firebasedb.OrderDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IOrderDBTable

class ProfileModel(
    private val presenter: IProfileContract.Presenter
) : IProfileContract.Model {

    private val orderDBTable: IOrderDBTable = OrderDBTable()

    override fun getOrdersList() {
        orderDBTable.getOrderList(this)
    }

    override fun getOrdersListResult(orders: MutableList<Order>) {
        presenter.updateHistoryAdapter(orders)
    }
}