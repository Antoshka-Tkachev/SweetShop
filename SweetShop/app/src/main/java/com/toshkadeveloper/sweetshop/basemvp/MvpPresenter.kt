package com.toshkadeveloper.sweetshop.basemvp

interface MvpPresenter<V : MvpView> {
    fun attachView(mvpView: V)
    fun viewIsReady()
    fun detachView()
    fun destroy()
}