package com.toshkadeveloper.sweetshop.mvp.basecatalog

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.User

abstract class BaseCatalogPresenter :
    PresenterBase<IBaseCatalogContract.View>(),
    IBaseCatalogContract.Presenter {

    protected lateinit var model: IBaseCatalogContract.Model
    protected var categories: MutableMap<String, Int> = mutableMapOf()
    protected var catalog: MutableList<Any> = mutableListOf()

    protected abstract fun initial()

    override fun viewIsReady() {
        initial()
        model.getCategories()
    }

    override fun addProductInBasket(product: Product) {
        model.addProductInBasket(product)
    }

    override fun addProductInBasketResult() {
        getView().showToastAddInBasket()
    }

    override fun addProductInFavorites(product: Product) {
        model.addProductInFavorites(product)
    }

    override fun addProductInFavoritesResult() {
        getView().showToastAddInFavorites()
    }

    override fun getUser(): User {
        return getView().getHomeActivityCallback().getUser()
    }

}