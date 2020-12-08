package com.toshkadeveloper.sweetshop.mvp.favorites

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.repository.firebasedb.FavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductInBasketDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IFavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class FavoriteModel(
    private val presenter: IFavoriteContract.Presenter,
    private val productInBasketDBTable: IProductInBasketDBTable = ProductInBasketDBTable("")
) : IFavoriteContract.Model {

    private val favoriteDBTable: IFavoriteDBTable = FavoriteDBTable()

    override fun getFavorites() {
        favoriteDBTable.getFavorites(this)
    }

    override fun getFavoritesResult(products: MutableList<Product>) {
       presenter.setFavoritesProduct(products)
    }

    override fun addProductInBasket(product: Product) {
        productInBasketDBTable.addProductInBasket(product, this)
    }

    override fun addProductInBasketResult() {
        presenter.addProductInBasketResult()
    }

}