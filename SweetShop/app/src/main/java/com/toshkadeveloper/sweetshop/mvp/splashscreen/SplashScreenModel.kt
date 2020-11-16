package com.toshkadeveloper.sweetshop.mvp.splashscreen

import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.repository.firebasedb.UserDBTable
import com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces.IUserDBTable

class SplashScreenModel(private val presenter: ISplashScreenContract.Presenter) : ISplashScreenContract.Model {

    private val userDBTable: IUserDBTable = UserDBTable()

    override fun isExistsUser(uid: String) {
        userDBTable.isExistUser(uid, this)
    }

    override fun isExistsUserResult(isExists: Boolean, uid: String) {
        presenter.setUserByUidRequest(isExists, uid)
    }

    override fun setUserByUid(uid: String) {
        userDBTable.setUserByUid(uid, this)
    }

    override fun setUserByUidResult(user: User) {
        presenter.updateUser(user)
    }

    override fun getUserByUid(uid: String) {
        userDBTable.getUserByUid(uid, this)
    }

    override fun getUserByUidResult(user: User) {
        presenter.updateUser(user)
    }

}