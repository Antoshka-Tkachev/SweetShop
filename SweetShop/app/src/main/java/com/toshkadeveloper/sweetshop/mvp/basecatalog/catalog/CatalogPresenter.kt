package com.toshkadeveloper.sweetshop.mvp.basecatalog.catalog

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogPresenter
import com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog.SearchCatalogFragment

class CatalogPresenter() : BaseCatalogPresenter() {
    private var isFirstPageProductsLoaded: Boolean = false
    private var isCategoriesLoaded: Boolean = false

    override fun initial() {
        model = CatalogModel(this)
    }

    override fun onClickSearch(_searchText: String) {
        if (_searchText.isEmpty()) {
            getView().setVisibleSearchView()
        } else {
            /**Если прошли все проверки на ввод*/
            getView().setTextSearchView("")
            val searchFragment = SearchCatalogFragment(_searchText, getView().getHomeActivityCallback())
            getView().getHomeActivityCallback().setSearchFragment(searchFragment)
        }
    }

    override fun setCatalogAndCategoriesData(_categories: MutableMap<String, Int>, _products: MutableList<Product>) {
        if (_categories.isNotEmpty()) {
            categories = _categories
        }
        if (isFirstPageProductsLoaded && isCategoriesLoaded) {
            getView().setAdapterInCategories(this.categories.keys.toMutableList(), this.categories.values.toMutableList())
            getView().showCatalogLoader(false)
            categories = mutableMapOf()
        }
    }

    override fun onFirstPageProductsLoaded(_isFirstPageProductsLoaded: Boolean) {
        isFirstPageProductsLoaded = _isFirstPageProductsLoaded
    }

    override fun onCategoriesLoaded(_isCategoriesLoaded: Boolean) {
        isCategoriesLoaded = _isCategoriesLoaded
    }

}