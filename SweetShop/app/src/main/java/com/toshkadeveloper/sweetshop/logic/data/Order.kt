package com.toshkadeveloper.sweetshop.logic.data

import java.io.Serializable

data class Order(
    val id: String = "",
    val total_price: Int = 0,
    val status: String = "",
    val time_of_registration: Long = 0,
    val time_of_delivery: Long = 0,
    val count_of_products: Int = 0,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val products: MutableList<ProductInBasket> = mutableListOf()
) : Serializable