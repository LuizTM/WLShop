package dev.luiztm.wlshop.data.datasources

import dev.luiztm.sa_network.SANetwork
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

class WLShopRemoteDataSourceImpl(private val saNetwork: SANetwork) : WLShopRemoteDataSource {
    override suspend fun products(): Result<List<ProductsResponseItem>> =
        saNetwork.makeRequest("products")

    override suspend fun productsByID(id:Int): Result<ProductsResponseItem> =
        saNetwork.makeRequest("products/$id")
}