package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract

interface IProductDescriptionDBTable {
    fun getProducts(keyProduct: String, model: IProductDescriptionContract.Model)
}