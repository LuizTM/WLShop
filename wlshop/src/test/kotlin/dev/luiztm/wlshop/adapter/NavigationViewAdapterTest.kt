package dev.luiztm.wlshop.adapter

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textview.MaterialTextView
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.view.adapter.NavigationItem
import dev.luiztm.wlshop.view.adapter.NavigationViewAdapter
import dev.luiztm.wlshop.view.fragments.routes.NavigationRoutes
import io.mockk.Awaits
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.runs
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
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
class NavigationViewAdapterTest {

    private val spyClick: (NavigationItem) -> Unit = spyk()

    private val adapter: NavigationViewAdapter = NavigationViewAdapter(
        listOf(
            NavigationItem(R.drawable.wlshop_ic_home_black_24dp, "Home", NavigationRoutes.home),
            NavigationItem(R.drawable.wlshop_ic_shopping_cart_24, "Cart", NavigationRoutes.cart)
        ), spyClick
    )

    private fun customApplication() = ContextThemeWrapper(
        ApplicationProvider.getApplicationContext(),
        R.style.WLShopThemeMediumContrast
    )

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Verify that first item is Home and stay enabled`() {
        val viewGroup = mockk<ViewGroup>(relaxed = true)
        val viewHolder = mockk<NavigationViewAdapter.ViewHolder>(relaxed = true, relaxUnitFun = true) {
            every { view } returns LayoutInflater.from(customApplication())
                .inflate(R.layout.wlshop_bottom_navigation_item_view, viewGroup, false)
        }
        adapter.onBindViewHolder(viewHolder, 0)

        assertThat(viewHolder.view.findViewById<TextView>(R.id.wlshop_nav_label).text).isEqualTo("Home")
        assertThat(viewHolder.view.findViewById<TextView>(R.id.wlshop_nav_label).visibility).isEqualTo(View.VISIBLE)

        adapter.onBindViewHolder(viewHolder, 1)

        assertThat(viewHolder.view.findViewById<TextView>(R.id.wlshop_nav_label).text).isEqualTo("Cart")
        assertThat(viewHolder.view.findViewById<TextView>(R.id.wlshop_nav_label).visibility).isEqualTo(View.GONE)
    }
}