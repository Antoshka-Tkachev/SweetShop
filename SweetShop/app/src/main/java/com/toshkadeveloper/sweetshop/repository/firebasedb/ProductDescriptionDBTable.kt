package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.database.*
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.mvp.productdescription.IProductDescriptionContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IProductDescriptionDBTable

class ProductDescriptionDBTable : IProductDescriptionDBTable {

    private val KEY_PRODUCT_DESCRIPTION: String = "PRODUCT_DESCRIPTION"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_PRODUCT_DESCRIPTION)

    override fun getProducts(keyProduct: String, model: IProductDescriptionContract.Model) {
        val queryProduct = database.orderByKey().equalTo(keyProduct)
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val product = dataSnapshot.children.first().getValue(ProductDescription::class.java)
                if(product != null) {
                    model.getProductInformationResult(product)
                }
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }
}