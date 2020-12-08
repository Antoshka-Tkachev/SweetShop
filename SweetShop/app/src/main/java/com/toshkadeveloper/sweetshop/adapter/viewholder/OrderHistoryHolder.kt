package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.content.Intent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.Order
import com.toshkadeveloper.sweetshop.mvp.orderdesc.OrderDescriptionActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderHistoryHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {

    private val iv_photo: ImageView = itemView.findViewById(R.id.iv_orderPhoto_history)
    private val tv_number: TextView = itemView.findViewById(R.id.tv_orderNumber_history)
    private val tv_date: TextView = itemView.findViewById(R.id.tv_date_history)
    private val tv_price: TextView = itemView.findViewById(R.id.tv_price_history)
    private val tv_count: TextView = itemView.findViewById(R.id.tv_countProducts_history)
    private val tv_status: TextView = itemView.findViewById(R.id.tv_orderStatus_history)
    private val fl_photosLoader: FrameLayout = itemView.findViewById(R.id.fl_photosLoader_history)
    private val cv_itemOrder: CardView = itemView.findViewById(R.id.cv_item_order_in_history)

    fun bind(position: Int, order: Order) {
        loadAndSetPhoto(order)
        tv_number.text = order.id
        val price = "${order.total_price} ₽"
        tv_price.text = price
        val format = SimpleDateFormat("dd MMM yyyy")
        val calendar = GregorianCalendar()
        calendar.timeInMillis = order.time_of_registration
        val date = "от ${format.format(calendar.time)}"
        tv_date.text = date
        val count = "${order.count_of_products} товаров"
        tv_count.text = count
        tv_status.text = order.status
        cv_itemOrder.setOnClickListener {
            val intent = Intent(itemView.context, OrderDescriptionActivity::class.java)
            intent.putExtra("ORDER", order)
            itemView.context.startActivity(intent)
        }

    }

    private fun loadAndSetPhoto(order: Order) {
        fl_photosLoader.visibility = View.VISIBLE
        Picasso.with(itemView.context).cancelRequest(iv_photo)
        Picasso.with(itemView.context)
            .load(order.products[0].photoUri)
            .into(iv_photo, object : Callback {
                override fun onSuccess() {
                    fl_photosLoader.visibility = View.GONE
                }

                override fun onError() {
                    fl_photosLoader.visibility = View.GONE
                    iv_photo.setImageResource(R.drawable.no_photo)
                }
            })
    }

}