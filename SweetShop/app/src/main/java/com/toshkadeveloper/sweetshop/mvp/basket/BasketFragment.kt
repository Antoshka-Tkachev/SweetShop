package com.toshkadeveloper.sweetshop.mvp.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.BasketAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.IBasketAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.ICategoriesAdapter
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_basket.*
import kotlinx.android.synthetic.main.fragment_basket.tv_price_basket
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.item_product_in_basket.*

class BasketFragment : Fragment(), IBasketContract.View, View.OnClickListener {

    private lateinit var basketAdapter: IBasketAdapter
    private lateinit var presenter: IBasketContract.Presenter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        initViews()
        initListeners()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun updateAdapter(products: MutableList<ProductInBasket>) {
        basketAdapter = BasketAdapter(requireContext(), products)
        rv_basket.adapter = basketAdapter as BasketAdapter
        if (products.isEmpty()) {
            rl_orderPanel_basket.visibility = View.GONE
        }
    }

    override fun getHomeActivity(): HomeActivity {
        return activity as HomeActivity
    }

    override fun showLoader(isShow: Boolean) {
        if (isShow) {
            fl_loaderContainer_basket.visibility = View.VISIBLE
            rv_basket.visibility = View.GONE
            v_shadowBottomNavigation_basket.visibility = View.GONE
            rl_orderPanel_basket.visibility = View.GONE

        } else {
            fl_loaderContainer_basket.visibility = View.GONE
            rv_basket.visibility = View.VISIBLE
            v_shadowBottomNavigation_basket.visibility = View.VISIBLE
            rl_orderPanel_basket.visibility = View.VISIBLE
        }
    }

    override fun updateOrderPanel(price: Int, count: Int) {
        val priceText = "$price ₽"
        val countText = "$count товаров"
        tv_price_basket.text = priceText
        tv_count_basket.text = countText
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ll_order_basket -> onClickOrder()
        }
    }

    private fun initial() {
        presenter = BasketPresenter()
        linearLayoutManager = LinearLayoutManager(requireContext())
    }

    private fun initViews() {
        rv_basket.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

    private fun initListeners() {
        ll_order_basket.setOnClickListener(this)
    }

    private fun onClickOrder() {

    }

}