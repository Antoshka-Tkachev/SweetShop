package com.toshkadeveloper.sweetshop.mvp.productdescription

import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket

class ProductDescriptionPresenter(
    private val keyProduct: String
) : PresenterBase<IProductDescriptionContract.View>(),
    IProductDescriptionContract.Presenter {

    private val model: IProductDescriptionContract.Model = ProductDescriptionModel(this)
    private lateinit var product: Product
    private lateinit var productDescription: ProductDescription
    private lateinit var productInBasket: ProductInBasket
    private var isProductInBasket: Boolean = false

    override fun viewIsReady() {
        model.getProductInformation(keyProduct)
    }

    override fun setProductInformation(product: ProductDescription) {
        productDescription = product
        getView().showProductInformation(product)
        getView().setBasketPanel()
    }

    override fun addProductInBasket(product: Product) {
        model.addProductInBasket(product)
    }

    override fun addProductInBasketResult() {
        getView().showToastAddInBasket()
    }

    override fun setProduct(_product: Product) {
        product = _product
    }

    override fun setProductInBasket(product: ProductInBasket) {
        productInBasket = product
        isProductInBasket = true
    }

    override fun getProduct(): Product {
        return product
    }

    override fun getProductInBasket(): ProductInBasket {
        return productInBasket
    }

    override fun isProductInBasket(): Boolean {
        return isProductInBasket
    }

    override fun addProductInFavorites(product: Product) {
        model.addProductInFavorites(product)
    }

    override fun addProductInFavoritesResult() {
        getView().showToastAddInFavorites()
    }
}