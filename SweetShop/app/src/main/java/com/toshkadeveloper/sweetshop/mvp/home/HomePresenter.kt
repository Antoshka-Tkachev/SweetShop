package com.toshkadeveloper.sweetshop.mvp.home

import android.util.Log
import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.User

class HomePresenter : PresenterBase<IHomeContract.View>(), IHomeContract.Presenter  {
    private lateinit var user: User
    override fun setUser(_user: User) {
        user = _user
    }

    override fun getUser(): User {
        return user
    }

    override fun viewIsReady() {
        Log.d("Auth", "uid = " + user.uid)
    }
}