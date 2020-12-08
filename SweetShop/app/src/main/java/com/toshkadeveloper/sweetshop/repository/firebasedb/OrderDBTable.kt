package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.order.IOrderContract
import com.toshkadeveloper.sweetshop.mvp.profile.IProfileContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IOrderDBTable

class OrderDBTable : IOrderDBTable {
    private val KEY_ORDER: String = "ORDER"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_ORDER)

    init {
        val auth: FirebaseAuth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            database = FirebaseDatabase.getInstance().getReference(KEY_ORDER).child(currentUser.uid)
        }
    }

    override fun setOrder(order: Order, model: IOrderContract.Model) {
        val orderMap = mutableMapOf<String, Order>()
        orderMap[order.id] = order
        database.updateChildren(orderMap.toMap()).addOnSuccessListener {
            model.setOrderResult(order.id)
        }
    }

    override fun getOrderList(model:IProfileContract.Model) {
        val queryProduct = database.orderByKey()
        queryProduct.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val orders = resultProcessing(dataSnapshot)
                model.getOrdersListResult(orders)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) { }
        })
    }

    private fun resultProcessing(dataSnapshot: DataSnapshot): MutableList<Order> {
        val order = mutableListOf<Order>()
        var bufferOrder: Order?
        for (postSnapshot in dataSnapshot.children) {
            bufferOrder = postSnapshot.getValue(Order::class.java)
            if (bufferOrder != null) {
                order.add(bufferOrder)
            }
        }
        order.reverse()
        return order
    }

}