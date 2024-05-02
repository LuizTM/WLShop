package dev.luiztm.wlshop.view.fragments.cart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import dev.luiztm.wlshop.di.injectActivityViewModel
import dev.luiztm.wlshop.view.fragments.home.adapter.CartItemViewAdapter
import dev.luiztm.wlshop.view.fragments.home.adapter.OverlapDecoration
import dev.luiztm.wlshop.view.fragments.home.adapter.ProductsItemViewAdapter
import dev.luiztm.wlshop.view.model.UIStateHome
import dev.luiztm.wlshop.view.model.WLShopViewModel
import kotlinx.coroutines.launch

class CartFragment : Fragment(R.layout.wlshop_home_cart) {

    private val viewmodel: WLShopViewModel by injectActivityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.wls_rv_cart)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        observersRemoteData {
            recyclerView.adapter = CartItemViewAdapter(it, {})
        }

        viewmodel.getAllCartProducts()
    }

    private fun observersRemoteData(setupView: (List<CartResponseItem>) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.productItems.collect {
                    when (it) {
                        is UIStateHome.Success -> {}

                        is UIStateHome.Error -> {}
                        is UIStateHome.Loading -> {}
                        is UIStateHome.SuccessCart -> {
                            setupView(it.data)
                        }
                    }
                }
            }
        }
    }

}