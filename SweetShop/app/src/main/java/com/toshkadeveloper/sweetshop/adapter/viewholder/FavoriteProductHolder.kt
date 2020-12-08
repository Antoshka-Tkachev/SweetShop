package com.toshkadeveloper.sweetshop.adapter.viewholder

import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.dynamic.IFragmentWrapper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.mvp.basecatalog.IBaseCatalogContract
import com.toshkadeveloper.sweetshop.mvp.favorites.IFavoriteContract
import com.toshkadeveloper.sweetshop.mvp.productdescription.ProductDescriptionActivity


class FavoriteProductHolder(
    itemView: View,
    private val callbackFragment: IFavoriteContract.View
) : CatalogItemHolder(itemView) {

    private val iv_like: ImageView = itemView.findViewById(R.id.iv_like_catalog)
    private val tv_name: TextView = itemView.findViewById(R.id.tv_name_catalog)
    private val tv_price: TextView = itemView.findViewById(R.id.tv_price_catalog)
    private val iv_productPhoto: ImageView = itemView.findViewById(R.id.iv_photo_catalog)
    private val fl_photosLoader: View = itemView.findViewById(R.id.fl_photosLoader_catalog)
    private val tv_addToBasket: TextView = itemView.findViewById(R.id.tv_addToBasket_catalog)


    override fun bind(position: Int, product: Any) {
        if (product is Product) {
            loadAndSetPhoto(product)
            tv_name.text = product.name
            tv_price.text = Html.fromHtml("<b>" + product.price  + " ₽ </b> за " + product.unit)
//            iv_like.setOnClickListener {
//                iv_like.setImageResource(R.drawable.ic_like_32)
//            }
            iv_like.setImageResource(R.drawable.button_dislike)
            iv_productPhoto.setOnClickListener {
                startProductDescriptionActivity(product)
            }
            tv_name.setOnClickListener {
                startProductDescriptionActivity(product)
            }
            tv_addToBasket.setOnClickListener {
                callbackFragment.addProductInBasket(product)
            }
        }
    }

    private fun startProductDescriptionActivity(product: Product) {
        val intent = Intent(itemView.context, ProductDescriptionActivity::class.java)
        intent.putExtra("KEY_PRODUCT_ID", product.id)
        intent.putExtra("KEY_PRODUCT", product)
        itemView.context.startActivity(intent)
    }

    private fun loadAndSetPhoto(product: Product) {
        iv_like.visibility = View.GONE
        fl_photosLoader.visibility = View.VISIBLE
        Picasso.with(itemView.context).cancelRequest(iv_productPhoto)
        Picasso.with(itemView.context)
            .load(product.photoUri)
            .into(iv_productPhoto, object : Callback {
                override fun onSuccess() {
                    fl_photosLoader.visibility = View.GONE
                    iv_like.visibility = View.VISIBLE
                }

                override fun onError() {
                    fl_photosLoader.visibility = View.GONE
                    iv_like.visibility = View.VISIBLE
                    iv_productPhoto.setImageResource(R.drawable.no_photo)
                }
            })
    }

}