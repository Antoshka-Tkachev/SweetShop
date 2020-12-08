package com.toshkadeveloper.sweetshop.mvp.orderdesc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.adapter.OrderAdapter
import com.toshkadeveloper.sweetshop.logic.data.Order
import kotlinx.android.synthetic.main.activity_order_description.*
import java.text.SimpleDateFormat
import java.util.*

class OrderDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_description)

        val order = this.intent.extras!!.getSerializable("ORDER") as Order

        rv_orderDesc.apply {
            layoutManager = LinearLayoutManager(this@OrderDescriptionActivity)
            adapter = OrderAdapter(this@OrderDescriptionActivity, order.products)
        }

        tv_nameText_orderDesc.text = order.name
        tv_phoneText_orderDesc.text = order.phone
        tv_emailText_orderDesc.text = order.email
        val format = SimpleDateFormat("dd MMM yyyy")
        val calendar = GregorianCalendar()
        calendar.timeInMillis = order.time_of_registration
        val date = "от ${format.format(calendar.time)}"
        tv_dateText_orderDesc.text = date
        tv_addressText_orderDesc.text = order.address
        tv_countText_orderDesc.text = order.count_of_products.toString()
        val price = "${order.total_price}  ₽"
        tv_priceText_orderDesc.text = price
    }
}