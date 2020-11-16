package com.toshkadeveloper.sweetshop.logic.data

import java.io.Serializable

class User(
    val uid: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val isAnonymous: Boolean = true
) : Serializable