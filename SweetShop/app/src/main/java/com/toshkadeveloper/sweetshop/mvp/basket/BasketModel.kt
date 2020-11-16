package com.toshkadeveloper.sweetshop.mvp.basket

import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductInBasketDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class BasketModel(
    private val presenter: IBasketContract.Presenter,
    private val uid: String
) : IBasketContract.Model {
    private val productInBasketDBTable: IProductInBasketDBTable = ProductInBasketDBTable(uid)

    override fun getProductsInBasket() {
        productInBasketDBTable.getProductsInBasket(this)
    }

    override fun getProductsInBasketResult(products: MutableList<ProductInBasket>) {
        presenter.updateAdapter(products)
    }
}