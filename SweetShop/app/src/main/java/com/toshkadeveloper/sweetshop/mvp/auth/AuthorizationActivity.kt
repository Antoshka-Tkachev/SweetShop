package com.toshkadeveloper.sweetshop.mvp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.mvp.registration.RegistrationActivity

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

    fun onClickSignUpAuth(v: View?) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

}