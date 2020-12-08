package com.toshkadeveloper.sweetshop.mvp.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.FavoritesAdapter
import com.toshkadeveloper.sweetshop.adapter.HistoryAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.IFavoritesAdapter
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogFragment
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity
import com.toshkadeveloper.sweetshop.mvp.profile.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_profile.*

class FavoriteFragment : Fragment(), IFavoriteContract.View, View.OnClickListener {

    private lateinit var presenter: IFavoriteContract.Presenter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var favoriteAdapter: IFavoritesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initial()
        setLayoutManager()
        initViews()
        initListeners()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    private fun initial() {
        presenter = FavoritePresenter()
        favoriteAdapter = FavoritesAdapter(mutableListOf(), requireContext(), this)
        gridLayoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
    }

    private fun initViews() {
        rv_favorite.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }
    }

    private fun initListeners() {
        btn_openCatalog_favorite.setOnClickListener(this)
    }

    private fun setLayoutManager() {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (favoriteAdapter.getItemViewType(position)) {
                    0 -> 2
                    1 -> 2
                    else -> 1
                }
            }
        }
    }

    private fun onClickOpenCatalog() {
        getHomeActivity().setCatalogFragment()
        getHomeActivity().setSelectedCatalogItem()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_openCatalog_favorite -> onClickOpenCatalog()
        }
    }

    override fun getHomeActivity(): HomeActivity {
        return activity as HomeActivity
    }

    override fun addProductInBasket(product: Product) {
        presenter.addProductInBasket(product)
    }

    override fun updateAdapter(catalog: MutableList<Any>) {
        favoriteAdapter = FavoritesAdapter(catalog, requireContext(), this)
        rv_favorite.adapter = favoriteAdapter as FavoritesAdapter
        if (catalog.isEmpty()) {
            fl_empty_favorite.visibility = View.VISIBLE
        } else {
            fl_empty_favorite.visibility = View.GONE
        }
    }

    override fun showLoader(isShow: Boolean) {
        if (isShow) {
            fl_loaderContainer_favorite.visibility = View.VISIBLE
            rv_favorite.visibility = View.GONE
        } else {
            fl_loaderContainer_favorite.visibility = View.GONE
            rv_favorite.visibility = View.VISIBLE
        }
    }

    override fun showToastAddInBasket() {
        Toast.makeText(requireContext(), "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val SPAN_COUNT = 2;
    }
}