package dev.luiztm.wlshop.data.model.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartResponseItem(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)