package com.toshkadeveloper.sweetshop.basemvp

abstract class PresenterBase<V : MvpView> : MvpPresenter<V> {
    private var view: V? = null

    override fun attachView(mvpView: V) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    fun getView() : V {
        if (isViewAttached())
            return view!!
        else
            throw Throwable("View is not attached")
    }

    fun isViewAttached() : Boolean {
        return view != null
    }

    override fun destroy() {
        TODO("Not yet implemented")
    }
}