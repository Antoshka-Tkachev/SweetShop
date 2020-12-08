package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.text.Html
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket

class ProductInOrderHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {

    private val iv_photo: ImageView = itemView.findViewById(R.id.iv_photo_itemOrder)
    private val tv_name: TextView = itemView.findViewById(R.id.tv_name_itemOrder)
    private val tv_price: TextView = itemView.findViewById(R.id.tv_priceProduct_itemOrder)
    private val tv_count: TextView = itemView.findViewById(R.id.tv_countText_itemOrder)
    private val tv_resultPrice: TextView = itemView.findViewById(R.id.tv_resultPrice_itemOrder)
    private val fl_photosLoader: FrameLayout = itemView.findViewById(R.id.fl_photosLoader_itemOrder)

    fun bind(position: Int, product: ProductInBasket) {
        Log.d("QWERTY", position.toString())
        loadAndSetPhoto(product)
        tv_name.text = product.name
        tv_price.text = Html.fromHtml("<b>" + product.price  + " ₽ </b> за " + product.unit)
        val countText = "${product.count}  шт."
        tv_count.text = countText
        val resultPriceText = "${product.price * product.count} ₽"
        tv_resultPrice.text = resultPriceText
    }

    private fun loadAndSetPhoto(product: ProductInBasket) {
        fl_photosLoader.visibility = View.VISIBLE
        Picasso.with(itemView.context).cancelRequest(iv_photo)
        Picasso.with(itemView.context)
            .load(product.photoUri)
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