package com.toshkadeveloper.sweetshop.mvp.basecatalog

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.repository.firebasedb.CategoryDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.FavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductInBasketDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.ICategoryDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IFavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

abstract class BaseCatalogModel(
    protected val presenter: IBaseCatalogContract.Presenter
) : IBaseCatalogContract.Model {
    private val productInBasketDBTable: IProductInBasketDBTable = ProductInBasketDBTable(presenter.getUser().uid)
    private val favoriteDBTable: IFavoriteDBTable = FavoriteDBTable()
    protected val categoryDBTable: ICategoryDBTable = CategoryDBTable()
    protected val productDBTable: IProductDBTable = ProductDBTable()

    override fun getCategories() {
        categoryDBTable.getCategories(this)
    }

    override fun getCategoriesResult(categories: MutableMap<String, Int>) {
        presenter.onCategoriesLoaded(true)
        presenter.setCatalogAndCategoriesData(categories)
    }

    override fun addProductInBasket(product: Product) {
        productInBasketDBTable.addProductInBasket(product, this)
    }

    override fun addProductInBasketResult() {
        presenter.addProductInBasketResult()
    }

    override fun addProductInFavorites(product: Product) {
        favoriteDBTable.addProductInFavorites(product, this)
    }

    override fun addProductInFavoritesResult() {
        presenter.addProductInFavoritesResult()
    }

}