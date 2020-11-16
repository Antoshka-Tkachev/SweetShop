package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.database.*
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.ICategoryDBTable

class CategoryDBTable : ICategoryDBTable {
    private val KEY_CATEGORY: String = "CATEGORY"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_CATEGORY)

    override fun getCategories(model: IBaseCatalogContract.Model) {
        val queryCategory = database.orderByKey()
        queryCategory.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                model.getCategoriesResult(resultProcessing(dataSnapshot))
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    private fun resultProcessing(dataSnapshot: DataSnapshot): MutableMap<String, Int> {
        val categories = mutableMapOf<String, Int>()
        var productsCountInCategory: Int?
        var categoryName: String?
        for (postSnapshot in dataSnapshot.children) {
            productsCountInCategory = postSnapshot.getValue(Int::class.java)
            categoryName = postSnapshot.key
            if (productsCountInCategory != null && categoryName != null) {
                categories[categoryName] = productsCountInCategory
            }
        }
        return categories
    }
}