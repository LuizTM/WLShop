package dev.luiztm.wlshop.view

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dev.luiztm.sa_analytics.SAAnalytics
import dev.luiztm.sa_analytics.injectSAAnalytics
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.di.injectViewModel
import dev.luiztm.wlshop.view.adapter.NavigationItem
import dev.luiztm.wlshop.view.components.BottomNavigationView
import dev.luiztm.wlshop.view.fragments.routes.NavigationRoutes
import dev.luiztm.wlshop.view.model.WLShopViewModel
import dev.luiztm.wlshop.view.navigation.setupRoutes

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
class WLShopHostActivity : AppCompatActivity(R.layout.wlshop_activity) {

    private val analytics: SAAnalytics by injectSAAnalytics()
    private val bottomNav: BottomNavigationView by lazy { findViewById(R.id.wlshop_bottom_nav_view) }

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectViewModel()
        analytics.trackScreen("WLShopHostActivity")

        navController.setupRoutes()

        bottomNav.setupView(
            listOf(
                NavigationItem(R.drawable.wlshop_ic_home_black_24dp, "Home", NavigationRoutes.home),
                NavigationItem(R.drawable.wlshop_ic_shopping_cart_24, "Cart", NavigationRoutes.cart),
            ), ::onSelectedNavigationItem
        )
    }


    //TODO Bug on back navigation by physic button, does not change icon selected
    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        bottomNav.setPreviousSelected()
        return super.getOnBackInvokedDispatcher()
    }

    private fun onSelectedNavigationItem(navItem: NavigationItem) {
        navController.navigate(navItem.route)
    }
}