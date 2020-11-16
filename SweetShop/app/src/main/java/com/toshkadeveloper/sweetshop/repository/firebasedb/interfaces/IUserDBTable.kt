package com.toshkadeveloper.sweetshop.repository.firebasedb.interfaces

import com.toshkadeveloper.sweetshop.mvp.splashscreen.ISplashScreenContract

interface IUserDBTable {
    fun getUserByUid(uid: String, model: ISplashScreenContract.Model)
    fun setUserByUid(uid: String, model: ISplashScreenContract.Model)
    fun isExistUser(uid: String, model: ISplashScreenContract.Model)
}