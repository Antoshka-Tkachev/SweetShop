package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract

interface ICategoryDBTable {
    fun getCategories(model: IBaseCatalogContract.Model)
}