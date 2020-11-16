package com.toshkadeveloper.sweetshop.mvp.splashscreen

import android.content.Intent
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toshkadeveloper.sweetshop.basemvp.PresenterBase
import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity

class SplashScreenPresenter : PresenterBase<ISplashScreenContract.View>(), ISplashScreenContract.Presenter {

    private lateinit var auth: FirebaseAuth
    private lateinit var user: User
    private val model: ISplashScreenContract.Model = SplashScreenModel(this)

    override fun viewIsReady() {
        auth = Firebase.auth
        var currentUser = auth.currentUser
        if (currentUser == null) {
            Log.d("Auth", "user == null")
            auth.signInAnonymously()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        currentUser = auth.currentUser
                        model.isExistsUser(currentUser!!.uid)
                    } else {
                        Log.d("Auth", "signInAnonymously:failure", task.exception)
                    }
                }
        } else {
            Log.d("Auth", "user != null")
            model.getUserByUid(currentUser!!.uid)
        }
    }

    override fun setUserByUidRequest(isExists: Boolean, uid: String) {
        if (isExists) {
            Log.d("Auth", "isExists = true")
            model.getUserByUid(uid)
        } else {
            Log.d("Auth", "isExists = false")
            model.setUserByUid(uid)
        }
    }

    override fun updateUser(_user: User) {
        user = _user
        getView().hideLoader()
        openHomeActivity()
    }

    private fun openHomeActivity() {
        /**Перевести на корутины*/
        Handler().postDelayed({
            val intent = Intent(getView().getViewContext(), HomeActivity::class.java)
            intent.putExtra("user", user)
            getView().apply {
                getViewContext().startActivity(intent)
                closeActivity()
            }
        }, 1000)
    }
}