package com.toshkadeveloper.sweetshop.logic.data

import java.io.Serializable

data class ProductInBasket(
    val id: String ="NoID",
    val name: String = "NoName",
    val price: Int = 0,
    val photoUri: String = "NoPhoto",
    val unit: String = "NoUnit",
    var count: Int = 0
) : Serializable