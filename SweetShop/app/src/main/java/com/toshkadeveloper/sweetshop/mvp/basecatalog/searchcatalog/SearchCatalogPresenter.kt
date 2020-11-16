package com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogPresenter
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class SearchCatalogPresenter(
    private val searchText:String
) : BaseCatalogPresenter() {

    override fun initial() {
        model = SearchCatalogModel(this)
    }

    override fun viewIsReady() {
        initial()
        getView().setTextSearchView(searchText)
        getView().showCatalogLoader(true)
        model.getProductByName(searchText)
    }

    override fun setCatalogAndCategoriesData(_categories: MutableMap<String, Int>, _products: MutableList<Product>) {
        if (_products.isEmpty()) {
            getView().showEmptyCatalog()
            return
        }
        _products.sortBy { product -> product.category }
        catalog = _products.toMutableList()
        addCategories()
        getView().setAdapterInCategories(this.categories.keys.toMutableList(), this.categories.values.toMutableList())
        getView().setAdapterInCatalog(catalog)
        getView().showCatalogLoader(false)
        categories = mutableMapOf()
        catalog = mutableListOf()
    }

    private fun addCategories() {
        var position = 0
        var categoryName = (catalog[position] as Product).category
        catalog.add(position,
            ProductCategory(categoryName)
        )
        categories[categoryName] = 0
        while (position != catalog.size - 1) {
            if (catalog[position] is Product &&
                catalog[position + 1] is Product &&
                (catalog[position] as Product).category != (catalog[position + 1] as Product).category
            ) {
                categoryName = (catalog[position + 1] as Product).category
                catalog.add(position + 1,
                    ProductCategory(
                        categoryName
                    )
                )
                categories[categoryName] = 0
            }
            position++
        }
    }

    override fun onClickSearch(_searchText: String) {
        if (searchText != _searchText) {
            val searchFragment = SearchCatalogFragment(_searchText, getView().getHomeActivityCallback())
            getView().getHomeActivityCallback().setSearchFragment(searchFragment)
        }
    }

    override fun onFirstPageProductsLoaded(_isFirstPageProductsLoaded: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onCategoriesLoaded(_isCategoriesLoaded: Boolean) {
        TODO("Not yet implemented")
    }
}