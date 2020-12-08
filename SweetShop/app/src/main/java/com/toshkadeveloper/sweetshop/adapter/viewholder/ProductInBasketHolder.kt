package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.mvp.productdescription.ProductDescriptionActivity

class ProductInBasketHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {

    private val iv_photo: ImageView = itemView.findViewById(R.id.iv_photo_basket)
    private val tv_name: TextView = itemView.findViewById(R.id.tv_name_basket)
    private val tv_price: TextView = itemView.findViewById(R.id.tv_priceProduct_basket)
    private val tv_count: TextView = itemView.findViewById(R.id.tv_countText_basket)
    private val tv_resultPrice: TextView = itemView.findViewById(R.id.tv_resultPrice_basket)
    private val ll_removeFromBasket: LinearLayout = itemView.findViewById(R.id.ll_removeFromBasket)
    private val fl_photosLoader: FrameLayout = itemView.findViewById(R.id.fl_photosLoader_basket)
    private val iv_like: ImageView = itemView.findViewById(R.id.iv_like_basket)

    fun bind(position: Int, product: ProductInBasket) {
        loadAndSetPhoto(product)
        tv_name.text = product.name
        tv_price.text = Html.fromHtml("<b>" + product.price  + " ₽ </b> за " + product.unit)
        val countText = "${product.count}  шт."
        tv_count.text = countText
        val resultPriceText = "${product.price * product.count} ₽"
        tv_resultPrice.text = resultPriceText
        iv_like.setOnClickListener {
            iv_like.setImageResource(R.drawable.ic_like_32)
        }
        iv_photo.setOnClickListener {
            val intent = Intent(itemView.context, ProductDescriptionActivity::class.java)
            intent.putExtra("KEY_PRODUCT_ID", product.id)
            intent.putExtra("KEY_PRODUCT_IN_BASKET", product)
            itemView.context.startActivity(intent)
        }
        tv_name.setOnClickListener {
            val intent = Intent(itemView.context, ProductDescriptionActivity::class.java)
            intent.putExtra("KEY_PRODUCT_ID", product.id)
            intent.putExtra("KEY_PRODUCT_IN_BASKET", product)
            itemView.context.startActivity(intent)
        }
        ll_removeFromBasket.setOnClickListener {
            /**Удалить товар из корзины*/
        }
    }

    private fun loadAndSetPhoto(product: ProductInBasket) {
        iv_like.visibility = View.GONE
        fl_photosLoader.visibility = View.VISIBLE
        Picasso.with(itemView.context).cancelRequest(iv_photo)
        Picasso.with(itemView.context)
            .load(product.photoUri)
            .into(iv_photo, object : Callback {
                override fun onSuccess() {
                    fl_photosLoader.visibility = View.GONE
                    iv_like.visibility = View.VISIBLE
                }

                override fun onError() {
                    fl_photosLoader.visibility = View.GONE
                    iv_like.visibility = View.VISIBLE
                    iv_photo.setImageResource(R.drawable.no_photo)
                }
            })
    }

}