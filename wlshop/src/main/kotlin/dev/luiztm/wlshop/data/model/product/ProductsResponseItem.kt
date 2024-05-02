package dev.luiztm.wlshop.data.model.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponseItem(
    val description: String,
    val id: Int,
    val category: String,
    val image: String,
    val price: Double,
    val title: String,
    val rating: Rating,
)

@Serializable
data class Products(
    val data: List<ProductsResponseItem>
)