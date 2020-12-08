package com.toshkadeveloper.sweetshop.mvp.order

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.logic.data.User
import java.util.*

class OrderPresenter : PresenterBase<IOrderContract.View>(), IOrderContract.Presenter {

    private lateinit var model: IOrderContract.Model
    private lateinit var user: User
    private var products = mutableListOf<ProductInBasket>()
    private var count = 0
    private var price = 0

    override fun getCount() = count

    override fun getPrice() = price

    override fun getProducts() = products

    override fun viewIsReady() {
        getView().showLoader(true)
        model = OrderModel(this, user.uid)
        model.getProductsInBasket()
    }

    override fun onClickOrdering(order: Order) {
        getView().showLoader(true)
        model.setOrder(order)
    }

    override fun setUser(_user: User) {
        user = _user
    }

    override fun updateAdapter(_products: MutableList<ProductInBasket>) {
        products = _products
        getView().showLoader(false)
        getView().updateAdapter(products)
        for (product in products) {
            count += product.count
            price += product.price * product.count
        }
        getView().updateInfoAboutProduct(price, count)
    }

    override fun orderIsProcessed(orderId: String) {
        getView().showLoader(false)
        getView().onCreateDialog(orderId).show()
    }
}