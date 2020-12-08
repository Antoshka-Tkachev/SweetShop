package com.toshkadeveloper.sweetshop.mvp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.User
import com.toshkadeveloper.sweetshop.mvp.basket.BasketFragment
import com.toshkadeveloper.sweetshop.mvp.basecatalog.catalog.CatalogFragment
import com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog.SearchCatalogFragment
import com.toshkadeveloper.sweetshop.mvp.favorites.FavoriteFragment
import com.toshkadeveloper.sweetshop.mvp.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), IHomeContract.View {

    private lateinit var transaction: FragmentTransaction
    private val presenter: IHomeContract.Presenter = HomePresenter()
    private val catalogFragment: CatalogFragment = CatalogFragment(this)
    private val favoriteFragment: FavoriteFragment = FavoriteFragment()
    private val basketFragment: BasketFragment = BasketFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()
    private var searchFragment: SearchCatalogFragment = SearchCatalogFragment(activityCallback = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter.apply {
            attachView(this@HomeActivity)
            setUser((this@HomeActivity.intent.extras!!.getSerializable("USER") as User))
            viewIsReady()
        }

        transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment_container, catalogFragment)
            commit()
        }


        initListener()
    }

    private fun initListener() {
        bottom_navigation_menu.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.item_catalog -> {
                    setSelectedFragment(catalogFragment)
                    true
                }
                R.id.item_favorites -> {
                    setSelectedFragment(favoriteFragment)
                    true
                }
                R.id.item_basket -> {
                    setSelectedFragment(basketFragment)
                    true
                }
                R.id.item_profile -> {
                    setSelectedFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectedFragment(fragment :Fragment) {
        transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    override fun setSearchFragment(fragment : SearchCatalogFragment) {
        searchFragment = fragment
        setSelectedFragment(searchFragment)
    }

    override fun setCatalogFragment() {
        setSelectedFragment(catalogFragment)
    }

    override fun setBasketFragment() {
        setSelectedFragment(basketFragment)
    }

    override fun setSelectedBasketItem() {
        bottom_navigation_menu.menu.getItem(2).isChecked = true
    }

    override fun getUser(): User {
        return presenter.getUser()
    }

    override fun setSelectedCatalogItem() {
        bottom_navigation_menu.menu.getItem(0).isChecked = true
    }

}