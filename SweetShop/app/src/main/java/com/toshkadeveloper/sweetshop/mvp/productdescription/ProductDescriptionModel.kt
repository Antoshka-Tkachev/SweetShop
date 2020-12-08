package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.repository.firebasedb.FavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductDescriptionDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.ProductInBasketDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IFavoriteDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDescriptionDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class ProductDescriptionModel(
    private val presenter: IProductDescriptionContract.Presenter
) : IProductDescriptionContract.Model {
    private val productDescriptionTable: IProductDescriptionDBTable = ProductDescriptionDBTable()
    private val productInBasketDBTable: IProductInBasketDBTable = ProductInBasketDBTable("")
    private val favoriteDBTable: IFavoriteDBTable = FavoriteDBTable()


    override fun getProductInformation(keyProduct: String) {
        productDescriptionTable.getProducts(keyProduct, this)
    }

    override fun getProductInformationResult(product: ProductDescription) {
        presenter.setProductInformation(product)
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