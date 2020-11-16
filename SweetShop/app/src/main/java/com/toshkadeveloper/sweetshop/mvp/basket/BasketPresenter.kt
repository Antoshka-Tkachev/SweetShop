package com.toshkadeveloper.sweetshop.mvp.basket

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.logic.data.User

class BasketPresenter : PresenterBase<IBasketContract.View>(), IBasketContract.Presenter {

    private lateinit var model: IBasketContract.Model
    private lateinit var user: User

    override fun viewIsReady() {
        getView().showLoader(true)
        user = getView().getHomeActivity().getUser()
        model = BasketModel(this, user.uid)
        model.getProductsInBasket()
    }

    override fun updateAdapter(products: MutableList<ProductInBasket>) {
        getView().showLoader(false)
        getView().updateAdapter(products)
        var count = 0
        var price = 0
        for (product in products) {
            count += product.count
            price += product.price * product.count
        }
        getView().updateOrderPanel(price, count)
    }
}