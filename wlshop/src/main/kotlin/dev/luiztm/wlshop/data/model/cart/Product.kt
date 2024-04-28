package dev.luiztm.wlshop.data.model.cart

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId: Int,
    val quantity: Int
)