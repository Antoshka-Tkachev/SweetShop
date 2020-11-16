package com.toshkadeveloper.sweetshop.mvp.productdescription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import kotlinx.android.synthetic.main.activity_product_description.*

class ProductDescriptionActivity : IProductDescriptionContract.View, AppCompatActivity() {
    private lateinit var presenter: IProductDescriptionContract.Presenter
    private var keyProduct: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        keyProduct = intent.getStringExtra("KEY_PRODUCT")!!

        presenter = ProductDescriptionPresenter(keyProduct)
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun showProductInformation(product: ProductDescription) {
        Picasso.with(this)
            .load(product.photoUri)
            .into(iv_photoProduct_description)

        tv_title_description.text = product.name
        tv_name_description.text = product.name

        val countInStock = "${product.countInStock} шт."
        tv_countInStock_description.text = countInStock

        val price = "${product.price}P/${product.unit}"
        tv_price_description.text = price

        tv_valueProteins_description.text = product.proteins.toString()
        tv_valueFats_description.text = product.fats.toString()
        tv_valueCarbohydrates_description.text = product.carbohydrates.toString()
        tv_valueCalories_description.text = product.calories.toString()

        tv_compositionText_description.text = product.composition


        val description =
            "Категория : ${product.category}\n" +
            "Вес: ${product.weight} г\n" +
            "Срок годности: ${product.shelfLife} дней\n" +
            "\n${product.description}"
        tv_descriptionText_description.text = description
    }
}