package dev.luiztm.wlshop.data.model.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponseItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)