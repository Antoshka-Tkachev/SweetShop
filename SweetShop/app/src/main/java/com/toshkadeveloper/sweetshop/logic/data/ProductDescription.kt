package com.toshkadeveloper.sweetshop.logic.data

data class ProductDescription(
    val id: String ="NoID",
    val name: String = "NoName",
    val price: Int = -1,
    val photoUri: String = "NoPhoto",
    val category: String = "NoCategory",
    val categoryAndPrice: String = "NoCategory_NoPrice",
    val unit: String = "NoUnit",
    val weight: Int = -1,
    val countInStock: Int = -1,
    val proteins: Int = -1,
    val fats: Int = -1,
    val carbohydrates: Int = -1,
    val calories: Int = -1,
    val shelfLife: Int = -1,
    val composition: String = "NoComposition",
    val description: String = "NoDescription"
)