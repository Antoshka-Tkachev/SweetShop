package com.toshkadeveloper.sweetshop.mvp.basecatalog.searchcatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.CatalogAdapter
import com.toshkadeveloper.sweetshop.adapter.SearchCategoriesAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ISearchCategoriesAdapter
import com.toshkadeveloper.sweetshop.mvp.basecatalog.BaseCatalogFragment
import com.toshkadeveloper.sweetshop.mvp.home.IHomeContract
import kotlinx.android.synthetic.main.fragment_search_catalog.*
import kotlinx.android.synthetic.main.fragment_search_catalog.rv_catalog
import kotlinx.android.synthetic.main.fragment_search_catalog.rv_category_catalog

class SearchCatalogFragment(
    private val searchText:String = "",
    activityCallback: IHomeContract.View
) : BaseCatalogFragment(activityCallback) {
    private lateinit var categoriesAdapter: ISearchCategoriesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_catalog, container, false)
    }

    override fun initial() {
        super.initial()
        presenter = SearchCatalogPresenter(searchText)
        /**Создать пустые адаптеры, в презентере запросить данные и после запроса запихнуть их в адаптер*/
        catalogAdapter = CatalogAdapter(requireContext(), gridLayoutManager, this)
        categoriesAdapter = SearchCategoriesAdapter(getViewContext(), catalogAdapter = catalogAdapter)
    }

    override fun initListeners() {
        super.initListeners()
        iv_clear_catalog.setOnClickListener(this)
        iv_back_catalog.setOnClickListener(this)
    }

    override fun showEmptyCatalog() {
        super.showEmptyCatalog()
        //добавить вывод заглушки
    }

    override fun setAdapterInCategories(names: MutableList<String>, productsCount: MutableList<Int>) {
        categoriesAdapter = SearchCategoriesAdapter(getViewContext(), names, productsCount, catalogAdapter)
        rv_category_catalog.adapter = categoriesAdapter as SearchCategoriesAdapter
    }

    override fun setAdapterInCatalog(catalog: MutableList<Any>) {
        catalogAdapter.getCatalog().addAll(catalog)
        rv_catalog.adapter = catalogAdapter as CatalogAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.iv_search_catalog -> onClickSearch()
            R.id.iv_clear_catalog -> setTextSearchView("")
            R.id.iv_back_catalog -> onClickBack()
        }
    }

    private fun onClickBack() {
        getHomeActivityCallback().setCatalogFragment()
    }

}