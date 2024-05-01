package dev.luiztm.wlshop.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import dev.luiztm.wlshop.view.fragments.CartFragment
import dev.luiztm.wlshop.view.fragments.WLShopHomeFragment
import dev.luiztm.wlshop.view.fragments.routes.NavigationRoutes


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


fun NavGraphBuilder.home() {
    fragment<WLShopHomeFragment>(NavigationRoutes.home) {
        label = "Home"
    }
}

fun NavGraphBuilder.cart() {
    fragment<CartFragment>(NavigationRoutes.cart) {
        label = "Cart"
//        argument(NavigationRoutes.plant_id) {
//            type = NavType.StringType
//        }
    }
}