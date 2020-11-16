package com.toshkadeveloper.sweetshop.mvp.splashscreen

import android.content.Context
import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.User

interface ISplashScreenContract {

    interface View : MvpView{
        fun hideLoader()
        fun closeActivity()
        fun getViewContext(): Context
    }

    interface Presenter : MvpPresenter<View> {
        fun updateUser(_user: User)
        fun setUserByUidRequest(isExists: Boolean, uid: String)
    }

    interface Model {
        fun isExistsUser(uid: String)
        fun isExistsUserResult(isExists: Boolean, uid: String)
        fun setUserByUid(uid: String)
        fun setUserByUidResult(user: User)
        fun getUserByUid(uid: String)
        fun getUserByUidResult(user: User)
    }
}