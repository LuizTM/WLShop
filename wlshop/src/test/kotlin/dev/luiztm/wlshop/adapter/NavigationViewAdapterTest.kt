package dev.luiztm.wlshop.view.adapter

import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.view.fragments.routes.NavigationRoutes
import org.junit.After
import org.junit.Before

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
class NavigationViewAdapterTest {

    private val spyClick:(NavigationItem) -> Unit = Spy
    private val adapter: NavigationViewAdapter = NavigationViewAdapter(
        listOf(
            NavigationItem(R.drawable.wlshop_ic_home_black_24dp, "Home", NavigationRoutes.home),
            NavigationItem(R.drawable.wlshop_ic_shopping_cart_24, "Cart", NavigationRoutes.cart)
        ), )

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}