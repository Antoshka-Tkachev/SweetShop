package com.toshkadeveloper.sweetshop.mvp.basecatalog.catalog

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.CatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.CategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.paginator.CatalogPaginator
import com.toshkadeveloper.sweetshop.logic.callback.ICatalogPaginatorCallback
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogFragment
import com.toshkadeveloper.sweetshop.mvp.home.IHomeContract
import kotlinx.android.synthetic.main.fragment_catalog.*

class CatalogFragment(
    activityCallback: IHomeContract.View
) : BaseCatalogFragment(activityCallback) {
    private lateinit var catalogPaginator: ICatalogPaginatorCallback
    private lateinit var categoriesAdapter: ICategoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun initial() {
        super.initial()
        presenter = CatalogPresenter()
        catalogAdapter = CatalogAdapter(requireContext(), gridLayoutManager, this)
        catalogPaginator = CatalogPaginator(catalogAdapter, this, presenter)
        categoriesAdapter = CategoriesAdapter(requireContext(), catalogAdapter = catalogAdapter, catalogPaginator = catalogPaginator)

    }

    override fun initListeners() {
        super.initListeners()
        rv_catalog.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == catalogAdapter.getItemCount() - 1) {
                    catalogPaginator.pageRequest()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.iv_search_catalog -> onClickSearch()
        }
    }

    override fun initViews() {
        super.initViews()
        rv_category_catalog.adapter = categoriesAdapter as CategoriesAdapter
        rv_catalog.adapter = catalogAdapter as CatalogAdapter
    }

    override fun setAdapterInCategories(names: MutableList<String>, productsCount: MutableList<Int>) {
        categoriesAdapter = CategoriesAdapter(requireContext(), names, productsCount, catalogAdapter, catalogPaginator = catalogPaginator)
        rv_category_catalog.adapter = categoriesAdapter as CategoriesAdapter
    }

    override fun setVisibleSearchView() {
        if (ll_searchView_catalog.visibility != View.VISIBLE) {
            tv_title_catalog.visibility = View.GONE
            ll_searchView_catalog.visibility = View.VISIBLE
            var searchViewAnim: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.open_search_view_anim)
            et_searchView_catalog.startAnimation(searchViewAnim)
        } else {
            var searchViewAnim: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.close_search_view_anim)
            et_searchView_catalog.startAnimation(searchViewAnim)
            /**Перевести на корутины*/
            Handler().postDelayed({
                tv_title_catalog.visibility = View.VISIBLE
                ll_searchView_catalog.visibility = View.GONE
            }, 300)
        }
    }

    override fun showEmptyCatalog() {
        super.showEmptyCatalog()
        //добавить вывод заглушки
    }

    override fun setAdapterInCatalog(catalog: MutableList<Any>) {
        TODO("Not yet implemented")
    }
}