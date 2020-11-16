package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.database.*
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.basket.IBasketContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductInBasketDBTable

class ProductInBasketDBTable(
    private val uid: String
) : IProductInBasketDBTable {
    private val KEY_PRODUCT_IN_BASKET: String = "PRODUCT_IN_BASKET"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_PRODUCT_IN_BASKET).child(uid)

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

    override fun addProductInBasket(product: Product, model: IBaseCatalogContract.Model) {
        val queryProduct = database.orderByKey().equalTo(product.id)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
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

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }
}