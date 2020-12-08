package com.toshkadeveloper.sweetshop.mvp.order

import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.repository.firebasedb.OrderDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductInBasketDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IOrderDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class OrderModel(
    private val presenter: IOrderContract.Presenter,
    private val uid: String
) : IOrderContract.Model {
    private val productInBasketDBTable: IProductInBasketDBTable = ProductInBasketDBTable(uid)
    private val orderDBTable: IOrderDBTable = OrderDBTable()

    override fun getProductsInBasket() {
        productInBasketDBTable.getProductsInBasket(this)
    }

    override fun getProductsInBasketResult(products: MutableList<ProductInBasket>) {
        presenter.updateAdapter(products)
    }

    override fun setOrder(order: Order) {
        orderDBTable.setOrder(order, this)
        productInBasketDBTable.removeBasket(uid)
    }

    override fun setOrderResult(orderId: String) {
        presenter.orderIsProcessed(orderId)
    }
}