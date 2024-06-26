package dev.luiztm.wlshop.data.repositories

import dev.luiztm.wlshop.data.db.CartEntity
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem


/**
Copyright (C) 2024 LuizTM

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
interface WLShopRepository {

    suspend fun getAllProducts(): Result<List<ProductsResponseItem>>
    suspend fun getProductByID(id: Int): Result<ProductsResponseItem>
    suspend fun addProductToCart(productId: Int)
    suspend fun getAllCartProducts(): Result<List<CartResponseItem>>
    suspend fun increaseCartItem(productId: Int)
    suspend fun decreaseCartItem(productId: Int)

//    suspend fun addCartProduct(id: Int): Result<Boolean>
//    suspend fun removeCartProduct(id: Int): Result<Boolean>
}