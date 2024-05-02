package dev.luiztm.wlshop.data.model.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartResponseItem(
    val id: Int = 0,
    val category: String,
    val image: String,
    val price: Double,
    val title: String,
    val quantity:Int
)