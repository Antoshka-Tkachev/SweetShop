package com.toshkadeveloper.sweetshop.mvp.profile

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.HistoryAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.IHistoryAdapter
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.mvp.auth.AuthorizationActivity
import com.toshkadeveloper.sweetshop.mvp.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), IProfileContract.View, View.OnClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var presenter: IProfileContract.Presenter
    private lateinit var historyAdapter: IHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        initViews()
        initListeners()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    private fun initial() {
        presenter = ProfilePresenter()
        linearLayoutManager = LinearLayoutManager(requireContext())
    }

    private fun initViews() {
        rv_historyOfOrders_profile.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

    private fun initListeners() {
        btn_openBasket_profile.setOnClickListener(this)
        rl_signIn_profile.setOnClickListener(this)
    }

    override fun updateHistoryAdapter(orders: MutableList<Order>) {
        historyAdapter = HistoryAdapter(requireContext(), orders)
        rv_historyOfOrders_profile.adapter = historyAdapter as HistoryAdapter
        if (orders.isEmpty()) {
            fl_emptyHistory_profile.visibility = View.VISIBLE
            tv_ordersHistory_profile.visibility = View.GONE
        } else {
            fl_emptyHistory_profile.visibility = View.GONE
            tv_ordersHistory_profile.visibility = View.VISIBLE
        }
    }

    override fun showLoader(isShow: Boolean) {
        if (isShow) {
            fl_loaderContainer_profile.visibility = View.VISIBLE
            rv_historyOfOrders_profile.visibility = View.GONE
            tv_ordersHistory_profile.visibility = View.GONE
        } else {
            fl_loaderContainer_profile.visibility = View.GONE
            rv_historyOfOrders_profile.visibility = View.VISIBLE
            tv_ordersHistory_profile.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_openBasket_profile -> onClickOpenBasket()
            R.id.rl_signIn_profile -> onClickSignInProfile()
        }
    }

    private fun onClickOpenBasket() {
        getHomeActivity().setBasketFragment()
        getHomeActivity().setSelectedBasketItem()
    }

    private fun onClickSignInProfile() {
        val intent = Intent(requireContext(), AuthorizationActivity::class.java)
        startActivity(intent)
    }

    override fun getHomeActivity(): HomeActivity {
        return activity as HomeActivity
    }

}