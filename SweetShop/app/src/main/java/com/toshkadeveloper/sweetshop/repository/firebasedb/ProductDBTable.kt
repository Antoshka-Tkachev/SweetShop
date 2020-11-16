package com.toshkadeveloper.sweetshop.repository.firebasedb

import android.util.Log
import com.google.firebase.database.*
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDBTable
import com.toshkadeveloper.sweetshop.logic.data.ProductCategory

class ProductDBTable : IProductDBTable {
    private val KEY_PRODUCT: String = "PRODUCT"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_PRODUCT)

    override fun getProductPage(productLastInformation: String, productCount: Int, callback: ICatalogPaginatorCallback) {
        val queryProduct = database.orderByKey().startAt(productLastInformation).limitToFirst(productCount)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = resultProcessing(dataSnapshot)
                products.removeAt(0)
                callback.productPageRequestCompleted(
                    products,
                    dataSnapshot.children.last().key.toString(),
                    dataSnapshot.childrenCount.toInt()
                )
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
                callback.productPageRequestCancelled()
            }
        })
    }

    override fun requestForScrollToCategory(productLastInformation: String, productCount: Int, callback: ICatalogPaginatorCallback, category: ProductCategory) {
        val queryProduct = database.orderByKey().startAt(productLastInformation).limitToFirst(productCount)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = resultProcessing(dataSnapshot)
                products.removeAt(0)
                callback.scrollToCategory(
                    products,
                    dataSnapshot.children.last().key.toString(),
                    dataSnapshot.childrenCount.toInt(),
                    category
                )
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
                callback.productPageRequestCancelled()
            }
        })
    }

    override fun getProductFirstPage(productCount: Int, callback: ICatalogPaginatorCallback) {
        val queryProduct = database.orderByKey().limitToFirst(productCount)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.productFirstPageRequestCompleted(
                    resultProcessing(dataSnapshot),
                    dataSnapshot.children.last().key.toString(),
                    dataSnapshot.childrenCount.toInt()
                )
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
                callback.productFirstPageRequestCancelled()
            }
        })
    }

    override fun getProductByName(name: String, model: IBaseCatalogContract.Model) {
        val queryProduct = database.orderByChild("name").startAt(name).endAt(name + "\uf8ff")
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                model.getProductByNameResult(resultProcessing(dataSnapshot))
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    private fun resultProcessing(dataSnapshot: DataSnapshot): MutableList<Product> {
        val products = mutableListOf<Product>()
        var bufferProduct: Product?
        for (postSnapshot in dataSnapshot.children) {
            bufferProduct = postSnapshot.getValue(Product::class.java)
            if (bufferProduct != null) {
                products.add(bufferProduct)
                Log.d("Log.Catalog product:", bufferProduct.toString())
            }
        }
        Log.d("QWERTY", "sizeSearch = ${products.size}")
        return products
    }
}