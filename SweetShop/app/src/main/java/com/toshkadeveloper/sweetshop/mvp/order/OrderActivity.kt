package com.toshkadeveloper.sweetshop.mvp.order

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.OrderAdapter
import com.toshkadeveloper.sweetshop.adapter.interfaces.IOrderAdapter
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.logic.data.User
import kotlinx.android.synthetic.main.activity_order.*
import java.util.*

class OrderActivity : AppCompatActivity(), IOrderContract.View {

    private val presenter: IOrderContract.Presenter = OrderPresenter()
    private var linearLayoutManager = LinearLayoutManager(this)
    private lateinit var orderAdapter: IOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initViews()

        presenter.setUser(intent.extras!!.getSerializable("USER") as User)

        presenter.attachView(this)
        presenter.viewIsReady()
    }

    private fun initViews() {
        rv_order.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

    fun onClickOrdering(v: View) {
        val date = Date().time
        Log.d("QWERTY", date.toString())
        presenter.onClickOrdering(Order(
                date.toString(),
                presenter.getPrice(),
                "В обработке",
                date,
                14,
                presenter.getCount(),
                et_name_order.text.toString(),
                et_email_order.text.toString(),
                et_phone_order.text.toString(),
                et_address_order.text.toString(),
                presenter.getProducts()
            )
        )
    }

    override fun onCreateDialog(orderNumber: String): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        return builder
            .setTitle("Заказ оформлен")
            .setIcon(R.drawable.ic_done_56)
            .setMessage("Номер заказа: $orderNumber")
            .setPositiveButton("Oк", null)
            .setOnDismissListener {
                setResult(Activity.RESULT_OK)
                finish()
            }
            .create()
    }


    override fun showLoader(isShow: Boolean) {
        if (isShow) {
            fl_loaderContainer_order.visibility = View.VISIBLE
            rv_order.visibility = View.GONE
            v_shadowBottomNavigation_order.visibility = View.GONE
            fl_orderPanel_order.visibility = View.GONE
            rl_infoAboutOrder_order.visibility = View.GONE
        } else {
            fl_loaderContainer_order.visibility = View.GONE
            rv_order.visibility = View.VISIBLE
            v_shadowBottomNavigation_order.visibility = View.VISIBLE
            fl_orderPanel_order.visibility = View.VISIBLE
            rl_infoAboutOrder_order.visibility = View.VISIBLE
        }
    }

    override fun updateAdapter(products: MutableList<ProductInBasket>) {
        orderAdapter = OrderAdapter(this, products)
        rv_order.adapter = orderAdapter as OrderAdapter
    }

    override fun updateInfoAboutProduct(price: Int, count: Int) {
        val priceText = "$price ₽"
        val countText = "$count"
        tv_priceText_order.text = priceText
        tv_countText_order.text = countText
    }

    fun onClickBackOrder(v: View) {
        onBackPressed()
    }

}