package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDBTable
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory
import com.toshkadeveloper.sweetshop.mvp.favorites.IFavoriteContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IFavoriteDBTable

class FavoriteDBTable : IFavoriteDBTable {
    private val KEY_FAVORITE: String = "FAVORITE"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_FAVORITE)

    init {
        val auth: FirebaseAuth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            database = FirebaseDatabase.getInstance().getReference(KEY_FAVORITE).child(currentUser.uid)
        }
    }

    override fun getFavorites(model: IFavoriteContract.Model) {
        val queryProduct = database.orderByKey()
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = resultProcessingGetFavorites(dataSnapshot)
                model.getFavoritesResult(products)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) { }
        })
    }

    override fun addProductInFavorites(product: Product, model: IBaseCatalogContract.Model) {
        val map = mutableMapOf<String, Product>()
        map[product.id] = product
        database.updateChildren(map as Map<String, Any>).addOnSuccessListener {
            model.addProductInFavoritesResult()
        }
    }

    override fun addProductInFavorites(product: Product, model: IProductDescriptionContract.Model) {
        val map = mutableMapOf<String, Product>()
        map[product.id] = product
        database.updateChildren(map as Map<String, Any>).addOnSuccessListener {
            model.addProductInFavoritesResult()
        }
    }

    private fun resultProcessingGetFavorites(dataSnapshot: DataSnapshot): MutableList<Product> {
        val products = mutableListOf<Product>()
        var bufferProduct: Product?
        for (postSnapshot in dataSnapshot.children) {
            bufferProduct = postSnapshot.getValue(Product::class.java)
            if (bufferProduct != null) {
                products.add(bufferProduct)
            }
        }
        return products
    }
}