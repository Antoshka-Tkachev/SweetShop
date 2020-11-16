package com.toshkadeveloper.sweetshop.mvp.splashscreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.toshkadeveloper.sweetshop.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(), ISplashScreenContract.View {

    private val presenter: ISplashScreenContract.Presenter = SplashScreenPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()
        presenter.apply {
            attachView(this@SplashScreenActivity)
            viewIsReady()
        }
    }

    override fun hideLoader() {
        pb_welcome.visibility = View.GONE
        tv_welcome.visibility = View.VISIBLE
        var openWelcome: Animation = AnimationUtils.loadAnimation(this, R.anim.open_welcome)
        tv_welcome.startAnimation(openWelcome)
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun closeActivity() {
        this.finish()
    }
}