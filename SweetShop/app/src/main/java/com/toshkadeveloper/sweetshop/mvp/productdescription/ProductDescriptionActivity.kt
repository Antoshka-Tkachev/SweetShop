package com.toshkadeveloper.sweetshop.mvp.productdescription

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import com.toshkadeveloper.sweetshop.logic.data.ProductInBasket
import com.toshkadeveloper.sweetshop.logic.data.User
import kotlinx.android.synthetic.main.activity_product_description.*

class ProductDescriptionActivity : IProductDescriptionContract.View, AppCompatActivity() {
    private lateinit var presenter: IProductDescriptionContract.Presenter
    private var keyProduct: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        keyProduct = intent.getStringExtra("KEY_PRODUCT_ID")!!

        presenter = ProductDescriptionPresenter(keyProduct)
        val product = intent.extras!!.getSerializable("KEY_PRODUCT") as Product?
        val productInBasket = intent.extras!!.getSerializable("KEY_PRODUCT_IN_BASKET") as ProductInBasket?

        if (product != null) {
            presenter.setProduct(product)
        }
        if (productInBasket != null) {
            presenter.setProductInBasket(productInBasket)
        }
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

        if(presenter.isProductInBasket()) {
            val count = presenter.getProductInBasket().count
            val countText = "$count шт."
            tv_countInBasket_description.text = countText
            val priceText = "${product.price * count}₽"
            tv_price_description.text = priceText
        } else {
            val price = "${product.price}₽/${product.unit}"
            tv_price_description.text = price
        }
    }


    override fun setBasketPanel() {
        if (presenter.isProductInBasket()) {
            iv_like_description.visibility = View.GONE
            ll_inBasket_description.visibility = View.INVISIBLE
            tv_countInBasket_description.visibility = View.VISIBLE
            ll_removeFromBasket_description.visibility = View.VISIBLE
        } else {
            iv_like_description.visibility = View.VISIBLE
            ll_inBasket_description.visibility = View.VISIBLE
            tv_countInBasket_description.visibility = View.GONE
            ll_removeFromBasket_description.visibility = View.GONE
        }
    }

    override fun showToastAddInBasket() {
        Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
    }

    override fun showToastAddInFavorites() {
        Toast.makeText(this, "Товар добавлен в избранное", Toast.LENGTH_SHORT).show()

    }

    fun onClickAddProductInBasket(v: View) {
        presenter.addProductInBasket(presenter.getProduct())
    }

    fun onClickAddProductInFavorites(v: View) {
        presenter.addProductInFavorites(presenter.getProduct())
    }

    fun onClickBackDescription(v: View) {
        onBackPressed()
    }
}