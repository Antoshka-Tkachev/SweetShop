package com.toshkadeveloper.sweetshop.logic.data

data class Product(
    val id: String ="NoID",
    val name: String = "NoName",
    val price: Int = 0,
    val photoUri: String = "NoPhoto",
    val category: String = "NoCategory",
    val categoryAndPrice: String = "NoCategory_NoPrice",
    val unit: String = "NoUnit"
)