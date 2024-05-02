package dev.luiztm.wlshop.view.fragments.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.sa_analytics.SAAnalytics
import dev.luiztm.sa_analytics.injectSAAnalytics
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import dev.luiztm.wlshop.di.injectActivityViewModel
import dev.luiztm.wlshop.view.fragments.home.adapter.OverlapDecoration
import dev.luiztm.wlshop.view.fragments.home.adapter.ProductsItemViewAdapter
import dev.luiztm.wlshop.view.model.UIStateHome
import dev.luiztm.wlshop.view.model.WLShopViewModel
import kotlinx.coroutines.launch


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
class WLShopHomeFragment : Fragment(R.layout.wlshop_home_fragment) {

    private val viewmodel: WLShopViewModel by injectActivityViewModel()
    private val analytics: SAAnalytics by injectSAAnalytics()
    private val progress: ProgressBar? by lazy { view?.findViewById(R.id.wlshop_home_progress) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen("WLShopHomeFragment")
        val recyclerView = view.findViewById<RecyclerView>(R.id.wls_rv_home)

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(OverlapDecoration())

        observersRemoteData {
            recyclerView.adapter = ProductsItemViewAdapter(it, ::onClickProductItem)
        }

        viewmodel.getAllProducts()
    }

    private fun onClickProductItem(product: ProductsResponseItem) {
        viewmodel.addProductToCart(productId = product.id)
        Toast.makeText(this.context, "Your item was sent to the cart!!", Toast.LENGTH_SHORT).show()
    }

    private fun observersRemoteData(setupView: (List<ProductsResponseItem>) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.productItems.collect {
                    when (it) {
                        is UIStateHome.Success -> {
                            progress?.visibility = View.GONE
                            setupView(it.data)
                        }

                        is UIStateHome.Error -> {}
                        is UIStateHome.Loading -> {
                            progress?.visibility = View.VISIBLE
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}