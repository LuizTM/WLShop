package dev.luiztm.wlshop.data.repositories

import dev.luiztm.wlshop.data.datasources.WLShopLocalDataSource
import dev.luiztm.wlshop.data.datasources.WLShopRemoteDataSource
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import java.time.Clock


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

class WLShopRepositoryImpl(
    private val remoteData: WLShopRemoteDataSource,
    private val localData: WLShopLocalDataSource
) : WLShopRepository {

    override suspend fun getAllProducts(): Result<List<ProductsResponseItem>> =
        getAndCache("products") { remoteData.products() }

    override suspend fun getProductByID(id: Int): Result<ProductsResponseItem> =
        getAndCache("productsByID$id") { remoteData.productsByID(id) }

    override suspend fun addProductToCart(productId: Int) {
        localData.addProduct(productId)
    }

    override suspend fun getAllCartProducts(): Result<List<CartResponseItem>> = runCatching {
        val list = localData.getAllCartProducts()
        list.getOrNull()!!.map {
            val product = getProductByID(it.productId).getOrThrow()
            CartResponseItem(
                id = it.productId,
                image = product.image,
                price = product.price,
                title = product.title,
                category = product.category,
                quantity = it.quantity,
            )
        }
    }

    override suspend fun decreaseCartItem(productId: Int) {
        localData.decreaseCartItem(productId)
    }

    override suspend fun increaseCartItem(productId: Int) {
        localData.increaseCartItem(productId)
    }

    private suspend fun <T : Any> getAndCache(
        key: String,
        block: suspend () -> T
    ): T {
        return localData.get(key)?.let {
            if (it.first + localData.expires > Clock.systemDefaultZone().millis()) {
                println("WLShopRepositoryImpl ->> FROM CACHE")
                it.second as T
            } else {
                println("WLShopRepositoryImpl ->> NOT FROM CACHE")
                block().also { response -> localData.set(key, response) }
            }
        } ?: block().also {
            println("WLShopRepositoryImpl ->> NOT FROM CACHE")
            localData.set(key, it)
        }
    }
}
