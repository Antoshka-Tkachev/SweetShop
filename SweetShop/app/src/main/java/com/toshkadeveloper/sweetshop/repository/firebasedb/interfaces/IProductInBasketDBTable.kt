package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.basket.IBasketContract

interface IProductInBasketDBTable {
    fun getProductsInBasket(model: IBasketContract.Model)
    fun addProductInBasket(product: Product, model: IBaseCatalogContract.Model)
}