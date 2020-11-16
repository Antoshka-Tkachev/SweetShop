package com.toshkadeveloper.sweetshop.repository.firebasedb

import com.google.firebase.database.*
import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.mvp.splashscreen.ISplashScreenContract
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IUserDBTable

class UserDBTable : IUserDBTable {
    private val KEY_PRODUCT: String = "USER"
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference(KEY_PRODUCT)

    override fun isExistUser(uid: String, model: ISplashScreenContract.Model) {
        val queryUser = database.orderByKey().startAt(uid).endAt(uid)
        queryUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                model.isExistsUserResult(dataSnapshot.childrenCount != 0L, uid)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun getUserByUid(uid: String, model: ISplashScreenContract.Model) {
        val queryUser = database.orderByKey().startAt(uid).endAt(uid)
        queryUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                model.getUserByUidResult(dataSnapshot.children.first().getValue(User::class.java)!!)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}
        })
    }

    override fun setUserByUid(uid: String, model: ISplashScreenContract.Model) {
        val usersMap = mutableMapOf<String, User>()
        val user = User(uid = uid)
        usersMap[uid] = user
        database.setValue(usersMap).addOnSuccessListener {
            model.setUserByUidResult(user)
        }
    }

}