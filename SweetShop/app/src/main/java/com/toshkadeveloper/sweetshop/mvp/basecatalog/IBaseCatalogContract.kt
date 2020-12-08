package com.toshkadeveloper.sweetshop.mvp.basecatalog

import android.content.Context
import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.mvp.home.IHomeContract

interface IBaseCatalogContract {
    interface View : MvpView {
        fun showCatalogLoader(isShow: Boolean)
        fun showEmptyCatalog()
        fun setAdapterInCategories(names: MutableList<String>, productsCount: MutableList<Int>)
        fun setAdapterInCatalog(catalog: MutableList<Any>)
        fun getViewContext(): Context
        fun setVisibleSearchView()
        fun getHomeActivityCallback(): IHomeContract.View
        fun setTextSearchView(text: String)
        fun addProductInBasket(product: Product)
        fun addProductInFavorites(product: Product)
        fun showToastAddInBasket()
        fun showToastAddInFavorites()
    }

    interface Presenter : MvpPresenter<View> {
        fun setCatalogAndCategoriesData(_categories: MutableMap<String, Int>  = mutableMapOf(), _products: MutableList<Product> = mutableListOf())
        fun onFirstPageProductsLoaded(_isFirstPageProductsLoaded: Boolean)
        fun onCategoriesLoaded(_isCategoriesLoaded: Boolean)
        fun onClickSearch(_searchText: String)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
        fun addProductInFavorites(product: Product)
        fun addProductInFavoritesResult()
        fun getUser(): User
    }

    interface Model {
        fun getCategories()
        fun getCategoriesResult(categories: MutableMap<String, Int>)
        fun getProductByName(productName: String)
        fun getProductByNameResult(products: MutableList<Product>)
        fun addProductInBasket(product: Product)
        fun addProductInBasketResult()
        fun addProductInFavorites(product: Product)
        fun addProductInFavoritesResult()
    }
}