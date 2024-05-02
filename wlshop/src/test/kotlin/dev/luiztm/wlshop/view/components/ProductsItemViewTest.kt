package dev.luiztm.wlshop.view.components

import android.view.ContextThemeWrapper
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.runner.RunWith

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
@RunWith(AndroidJUnit4::class)
class ProductsItemViewTest {

    private fun customApplication() = ContextThemeWrapper(
        ApplicationProvider.getApplicationContext(),
        R.style.WLShopThemeMediumContrast
    )

    @Test
    fun `Verify the correct initialization view with params`() {
        val productsItemView = ProductsItemView(context = customApplication())
        val product = stubProduct()
        productsItemView.setupView(
            name = product.title,
            price = product.price.toString(),
            image = product.image,
            rating = product.rating
        ) {}

        assertThat(productsItemView.findViewById<TextView>(R.id.wlshop_product_name).text).isEqualTo(
            product.title.take(30)
        )
        assertThat(productsItemView.findViewById<TextView>(R.id.wlshop_product_price).text).isEqualTo(
            "$${product.price}"
        )
        assertThat(productsItemView.findViewById<TextView>(R.id.wlshop_rating_value).text).isEqualTo(
            product.rating.rate.toString()
        )
    }

    private fun stubProduct() =
        Json.decodeFromString<ProductsResponseItem>(
            File("src/test/resources/product_1.json").readText()
        )
}