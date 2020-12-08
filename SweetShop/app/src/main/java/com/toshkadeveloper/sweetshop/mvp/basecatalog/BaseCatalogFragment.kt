package com.toshkadeveloper.sweetshop.mvp.basecatalog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICatalogAdapter
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.home.IHomeContract
import kotlinx.android.synthetic.main.fragment_catalog.*

abstract class BaseCatalogFragment(
    private val activityCallback: IHomeContract.View
) : Fragment(),
    IBaseCatalogContract.View,
    View.OnClickListener {

    protected lateinit var linearLayoutManager: LinearLayoutManager
    protected lateinit var gridLayoutManager: GridLayoutManager
    protected lateinit var catalogAdapter: ICatalogAdapter
    protected lateinit var presenter: IBaseCatalogContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        setLayoutManager()
        initListeners()
        initViews()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    protected open fun initial() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        gridLayoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
    }

    protected open fun initListeners() {
        iv_search_catalog.setOnClickListener(this)
    }

    override fun getHomeActivityCallback(): IHomeContract.View {
        return activityCallback
    }

    private fun setLayoutManager() {
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (catalogAdapter.getItemViewType(position)) {
                    0 -> 2
                    1 -> 2
                    else -> 1
                }
            }
        }
    }

    /**Не факт, что будет рабоатть для View с одинаковыми id*/
    protected open fun initViews() {
        rv_category_catalog.layoutManager = linearLayoutManager
        rv_catalog.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }
    }

    protected fun onClickSearch() {
        presenter.onClickSearch(et_searchView_catalog.text.toString())
    }

    override fun setTextSearchView(text: String) {
        et_searchView_catalog.setText(text)
    }

    /**удалить если будет переопределяться в серч фрагменте*/
    override fun setVisibleSearchView() { }

    override fun showCatalogLoader(isShow: Boolean) {
        if (isShow) {
            fl_loaderContainer_catalog.visibility = View.VISIBLE
            rv_catalog.visibility = View.GONE
            rv_category_catalog.visibility = View.GONE
        } else {
            fl_loaderContainer_catalog.visibility = View.GONE
            rv_catalog.visibility = View.VISIBLE
            rv_category_catalog.visibility = View.VISIBLE
        }
    }

    override fun showEmptyCatalog() {
        showCatalogLoader(false)
        rv_catalog.visibility = View.GONE
        rv_category_catalog.visibility = View.GONE
    }

    override fun getViewContext(): Context {
        return this.requireContext()
    }

    override fun addProductInBasket(product: Product) {
        presenter.addProductInBasket(product)
    }

    override fun addProductInFavorites(product: Product) {
        presenter.addProductInFavorites(product)
    }

    override fun showToastAddInBasket() {
        Toast.makeText(requireContext(), "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
    }

    override fun showToastAddInFavorites() {
        Toast.makeText(requireContext(), "Товар добавлен в избранное", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val SPAN_COUNT = 2
    }

}
