package com.toshkadeveloper.sweetshop.ztestfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.toshkadeveloper.sweetshop.R
import com.toshkadeveloper.sweetshop.logic.data.Product
import com.toshkadeveloper.sweetshop.logic.data.ProductDescription
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private val COUNT_PRODUCT = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickOk(v: View) {
        auth = Firebase.auth
        val currentUser = auth.currentUser
        Log.d("QWERTY", currentUser.toString())
        if (currentUser != null) {
            Log.d("QWERTY", currentUser.uid)
        }
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("QWERTY", "signInAnonymously:success")
                    val user = auth.currentUser
                    Log.d("QWERTY", user.toString())
                    if (user != null) {
                        Log.d("QWERTY", user.uid)
                    }
                } else {
                    Log.d("QWERTY", "signInAnonymously:failure", task.exception)
                }
            }
    }

//    fun onClickOk(v: View) {
//
//        /**Добавление продуктов, подробной информации о продуктах, категорий*/
//        database = FirebaseDatabase.getInstance().getReference("PRODUCT")
//        val productMap = mutableMapOf<String, Product>()
//        var random: Int
//        var id: String
//        var price: Int
//        var category: String
//        val listCategory = listOf("Выпечка", "Десерты", "Мороженное", "Пироженные", "Пастила", "Эклеры", "Зефир")
//        val listUnit = listOf("100 г.", "1 кг.", "1 шт.", "1 л.", "100 мл.")
//        val listCountCategory = mutableListOf(0, 0, 0, 0, 0, 0, 0)
//        val idsList = mutableListOf<String>()
//
//        for (i in 1..COUNT_PRODUCT) {
//            random = Random.nextInt(listCategory.size)
//            price = Random.nextInt(5000)
//            category = listCategory[random]
//            listCountCategory[random]++
//            id = listCategory[random] + "_" + String.format("%07d", listCountCategory[random])
//            idsList.add(id)
//
//            productMap[id] =
//                Product(
//                    id,
//                    "Продукт $i",
//                    price,
//                    "https://sovremennik.info/up/tort/pic/587/pic_1316_preview.jpg",
//                    category,
//                    category + "_" + String.format("%07d", price),
//                    listUnit[Random.nextInt(listUnit.size)]
//                )
//        }
//        database.setValue(productMap)
//
//        database = FirebaseDatabase.getInstance().getReference("CATEGORY")
//        val categoryMap = mutableMapOf<String, Int>()
//        for (i in listCategory.indices) {
//            categoryMap[listCategory[i]] = listCountCategory[i]
//        }
//        database.setValue(categoryMap)
//
//        database = FirebaseDatabase.getInstance().getReference("PRODUCT_DESCRIPTION")
//        val descriptionMap = mutableMapOf<String, ProductDescription>()
//        var newId: String
//        var weight: Int
//        var count_in_stock: Int
//        var proteins: Int
//        var fats: Int
//        var carbohydrates: Int
//        var calories: Int
//        var shelf_life: Int
//        var composition: String
//        var description: String
//
//        for (i in 1..COUNT_PRODUCT) {
//            newId = idsList[i - 1]
//            weight = Random.nextInt(2000)
//            count_in_stock = Random.nextInt(100)
//            proteins = Random.nextInt(100)
//            fats = Random.nextInt(100)
//            carbohydrates = Random.nextInt(100)
//            calories = Random.nextInt(100)
//            shelf_life = Random.nextInt(150)
//            composition = productMap[newId]!!.categoryAndPrice
//            description = "$composition Подробное описание товара, очень и очень подробное, настролько подробное, что можно узнать все о товаре. И даже больше. главное не перестараться, а то закончится трафик, и будет очень не удобно"
//
//            descriptionMap[newId] =
//                ProductDescription(
//                    newId,
//                    productMap[newId]!!.name,
//                    productMap[newId]!!.price,
//                    productMap[newId]!!.photoUri,
//                    productMap[newId]!!.category,
//                    productMap[newId]!!.categoryAndPrice,
//                    productMap[newId]!!.unit,
//                    weight,
//                    count_in_stock,
//                    proteins,
//                    fats,
//                    carbohydrates,
//                    calories,
//                    shelf_life,
//                    composition,
//                    description
//                )
//        }
//        database.setValue(descriptionMap)
//    }
}