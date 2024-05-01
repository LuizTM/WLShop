package dev.luiztm.wlshop.data.model.product

import com.squareup.moshi.Json
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

@Serializable
data class Products(
    val data: List<ProductsResponseItem>
)