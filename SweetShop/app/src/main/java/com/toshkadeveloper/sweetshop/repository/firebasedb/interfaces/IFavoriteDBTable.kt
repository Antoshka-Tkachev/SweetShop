package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.favorites.IFavoriteContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract

interface IFavoriteDBTable {
    fun getFavorites(model: IFavoriteContract.Model)
    fun addProductInFavorites(product: Product, model: IBaseCatalogContract.Model)
    fun addProductInFavorites(product: Product, model: IProductDescriptionContract.Model)
}