package com.toshkadeveloper.sweetshop.mvp.favorites

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class FavoritePresenter : PresenterBase<IFavoriteContract.View>(), IFavoriteContract.Presenter {
    private val model: IFavoriteContract.Model = FavoriteModel(this)
    private var catalog = mutableListOf<Any>()

    override fun viewIsReady() {
        getView().showLoader(true)
        model.getFavorites()
    }

    override fun addProductInBasket(product: Product) {
        model.addProductInBasket(product)
    }

    override fun addProductInBasketResult() {
        getView().showToastAddInBasket()
    }

    override fun setFavoritesProduct(_products: MutableList<Product>) {
        if (_products.isNotEmpty()) {
            catalog.addAll(_products)
            addCategory(0)
            addCategories()
        }
        getView().showLoader(false)
        getView().updateAdapter(catalog)
    }

    private fun addCategory(position: Int) {
        catalog.add(position,
            ProductCategory((catalog[position] as Product).category)
        )
    }

    private fun addCategories() {
        var position = 0
        while (position != catalog.size - 1) {
            if (catalog[position] is Product &&
                catalog[position + 1] is Product &&
                (catalog[position] as Product).category != (catalog[position + 1] as Product).category
            ) {
                addCategory(position + 1)
            }
            position++
        }
    }

}