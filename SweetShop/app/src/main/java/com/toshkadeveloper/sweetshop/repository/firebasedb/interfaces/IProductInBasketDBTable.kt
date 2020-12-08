package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.basket.IBasketContract
import com.toshkadeveloper.sweetshop.mvp.favorites.IFavoriteContract
import com.toshkadeveloper.sweetshop.mvp.order.IOrderContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract

interface IProductInBasketDBTable {
    fun getProductsInBasket(model: IBasketContract.Model)
    fun getProductsInBasket(model: IOrderContract.Model)
    fun addProductInBasket(product: Product, model: IBaseCatalogContract.Model)
    fun addProductInBasket(product: Product, model: IFavoriteContract.Model)
    fun addProductInBasket(product: Product, model: IProductDescriptionContract.Model)
    fun removeBasket(uid: String)
}