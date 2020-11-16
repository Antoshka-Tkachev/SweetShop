package com.toshkadeveloper.sweetshop.mvp.home

import com.toshkadeveloper.sweetshop.basemvp.MvpPresenter
import com.toshkadeveloper.sweetshop.basemvp.MvpView
import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog.SearchCatalogFragment

interface IHomeContract {

    interface View : MvpView {
        fun setSearchFragment(fragment : SearchCatalogFragment)
        fun setCatalogFragment()
        fun getUser(): User
    }

    interface Presenter : MvpPresenter<View> {
        fun setUser(_user: User)
        fun getUser(): User
    }

    interface Model {

    }
}