package dev.luiztm.wlshop.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.luiztm.wlshop.data.datasources.WLShopLocalDataSource
import dev.luiztm.wlshop.data.datasources.WLShopRemoteDataSource
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkAll
import io.mockk.verify
import java.io.File
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Copyright (C) 2024 LuizTM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class WLShopRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockRemote = mockRemoteDataSource()
    private val mockLocal = mockk<WLShopLocalDataSource>()

    private val repository = WLShopRepositoryImpl(
        mockRemote,
        mockLocal
    )

    @Before
    fun setUp() {
        val now = 50L
        val fixedClock = Clock.fixed(Instant.ofEpochMilli(now), ZoneId.systemDefault()).millis()

        mockkStatic(Clock::class)
        every { Clock.systemDefaultZone().millis() } returns fixedClock
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllProducts from API when cache is expired`() = runTest {
        mockLocalDataSourceAPIAndCache("products", ::stubProducts)
        repository.getAllProducts()

        coVerifyOrder {
            mockLocal.get(any())
            mockRemote.products()
            mockLocal.set(any(), any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllProducts from cache`() = runTest {
        mockLocalDataSourceCache("products", ::stubProducts)
        repository.getAllProducts()
        verify(exactly = 1) { mockLocal.get(any()) }
        verify(exactly = 0) { mockLocal.set(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getProductByID from API when cache is expired`() = runTest {
        mockLocalDataSourceAPIAndCache("productsByID1", ::stubProductByID)
        repository.getProductByID(1)

        coVerifyOrder {
            mockLocal.get(any())
            mockRemote.productsByID(1)
            mockLocal.set(any(), any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getProductByID from cache`() = runTest {
        mockLocalDataSourceCache("productsByID1", ::stubProductByID)
        repository.getProductByID(1)
        verify(exactly = 1) { mockLocal.get(any()) }
        verify(exactly = 0) { mockLocal.set(any(), any()) }
    }

    private fun mockRemoteDataSource() = mockk<WLShopRemoteDataSource> {
        coEvery { products() } returns stubProducts()
        coEvery { productsByID(any()) } returns stubProductByID()
    }

    private fun <T:Any> mockLocalDataSourceCache(key:String, stub:()->T) {
        every { mockLocal.expires } returns 10
        every { mockLocal.get(key) } returns Pair(100L, stub())
    }

    private fun <T:Any> mockLocalDataSourceAPIAndCache(key:String, stub:()->T){
        every { mockLocal.expires } returns 10
        every { mockLocal.get(key) } returns Pair(0L, stub())
        every { mockLocal.set(key, any()) } just runs
    }

    private fun stubProducts() = runCatching {
        Json.decodeFromString<List<ProductsResponseItem>>(
            File("src/test/resources/products.json").readText()
        )
    }

    private fun stubProductByID() = runCatching {
        Json.decodeFromString<ProductsResponseItem>(
            File("src/test/resources/product_1.json").readText()
        )
    }
}