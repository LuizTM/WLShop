package dev.luiztm.wlshop.view.fragments.cart

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.sa_analytics.SAAnalytics
import dev.luiztm.sa_analytics.injectSAAnalytics
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
import dev.luiztm.wlshop.di.injectActivityViewModel
import dev.luiztm.wlshop.view.fragments.home.adapter.CartItemViewAdapter
import dev.luiztm.wlshop.view.fragments.home.adapter.TypeButton
import dev.luiztm.wlshop.view.model.UIStateHome
import dev.luiztm.wlshop.view.model.WLShopViewModel
import kotlinx.coroutines.launch

class CartFragment : Fragment(R.layout.wlshop_home_cart) {

    private val viewmodel: WLShopViewModel by injectActivityViewModel()
    private val analytics: SAAnalytics by injectSAAnalytics()
    private val progress: ProgressBar? by lazy { view?.findViewById(R.id.wlshop_cart_progress) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen("CartFragment")
        val recyclerView = view.findViewById<RecyclerView>(R.id.wls_rv_cart)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        observersRemoteData {
            recyclerView.adapter = CartItemViewAdapter(it) { type, cart ->
                when (type) {
                    TypeButton.PLUS -> viewmodel.increaseCartItem(cart.id)
                    TypeButton.MINUS -> viewmodel.decreaseCartItem(cart.id)
                }
                viewmodel.getAllCartProducts()
            }
        }

        viewmodel.getAllCartProducts()
    }

    private fun observersRemoteData(setupView: (List<CartResponseItem>) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.productItems.collect {
                    when (it) {
                        is UIStateHome.Error -> {}
                        is UIStateHome.Loading -> {
                            progress?.visibility = View.VISIBLE
                        }
                        is UIStateHome.SuccessCart -> {
                            progress?.visibility = View.GONE
                            setupView(it.data)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

}