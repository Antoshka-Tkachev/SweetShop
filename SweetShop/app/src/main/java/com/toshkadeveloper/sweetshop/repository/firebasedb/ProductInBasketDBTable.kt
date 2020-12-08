package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.basket.IBasketContract
import com.toshkadeveloper.sweetshop.mvp.favorites.IFavoriteContract
import com.toshkadeveloper.sweetshop.mvp.order.IOrderContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.ProductDescriptionModel
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class ProductInBasketDBTable(
    private var uid: String
) : IProductInBasketDBTable {
    private val KEY_PRODUCT_IN_BASKET: String = "PRODUCT_IN_BASKET"
    private var database: DatabaseReference

    init {
        if (uid == "") {
            val auth: FirebaseAuth = Firebase.auth
            var currentUser = auth.currentUser
            if (currentUser != null) {
                uid = currentUser.uid
            }
        }
        database = FirebaseDatabase.getInstance().getReference(KEY_PRODUCT_IN_BASKET).child(uid)
    }

    override fun getProductsInBasket(model: IBasketContract.Model) {
        val queryProduct = database.orderByKey()
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val products = mutableListOf<ProductInBasket>()
                var bufferProduct: ProductInBasket?
                for (postSnapshot in dataSnapshot.children) {
                    bufferProduct = postSnapshot.getValue(ProductInBasket::class.java)
                    if (bufferProduct != null) {
                        products.add(bufferProduct)
                    }
                }
                model.getProductsInBasketResult(products)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun getProductsInBasket(model: IOrderContract.Model) {
        val queryProduct = database.orderByKey()
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val products = mutableListOf<ProductInBasket>()
                var bufferProduct: ProductInBasket?
                for (postSnapshot in dataSnapshot.children) {
                    bufferProduct = postSnapshot.getValue(ProductInBasket::class.java)
                    if (bufferProduct != null) {
                        products.add(bufferProduct)
                    }
                }
                model.getProductsInBasketResult(products)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun addProductInBasket(product: Product, model: IBaseCatalogContract.Model) {
        val queryProduct = database.orderByKey().equalTo(product.id)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                resultProcessing(dataSnapshot, product)
                model.addProductInBasketResult()
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun addProductInBasket(product: Product, model: IProductDescriptionContract.Model) {
        val queryProduct = database.orderByKey().equalTo(product.id)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                resultProcessing(dataSnapshot, product)
                model.addProductInBasketResult()
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun addProductInBasket(product: Product, model: IFavoriteContract.Model) {
        val queryProduct = database.orderByKey().equalTo(product.id)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                resultProcessing(dataSnapshot, product)
                model.addProductInBasketResult()
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun removeBasket(uid: String) {
        database.removeValue()
    }

    private fun resultProcessing(dataSnapshot: DataSnapshot, product: Product) {
        val productInBasket: ProductInBasket?
        val isExist = dataSnapshot.childrenCount != 0L
        if (isExist) {
            productInBasket = dataSnapshot.children.first().getValue(ProductInBasket::class.java)!!
            productInBasket.count++
        } else {
            productInBasket = ProductInBasket(
                product.id,
                product.name,
                product.price,
                product.photoUri,
                product.unit,
                1
            )
        }
        database.child(productInBasket.id).setValue(productInBasket)
    }
}