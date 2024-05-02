package dev.luiztm.wlshop.view.fragments.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
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
class CartItemViewAdapter(
    private val dataSet: List<CartResponseItem>,
    private val itemViewClick: (CartResponseItem) -> Unit
) : RecyclerView.Adapter<CartItemViewAdapter.ViewHolder>() {

    override fun getItemCount() = dataSet.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: AppCompatImageView = view.findViewById(R.id.wlshop_cart_image)
        val label: TextView = view.findViewById(R.id.wlshop_cart_name)
        val category: TextView = view.findViewById(R.id.wlshop_cart_category)
        val price: TextView = view.findViewById(R.id.wlshop_cart_price)
        val quantity: TextView = view.findViewById(R.id.wlshop_cart_value)
        val btnPlus: TextView = view.findViewById(R.id.wlshop_cart_plus)
        val btnMinus: TextView = view.findViewById(R.id.wlshop_cart_minus)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.wlshop_cart_item_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]

        with(viewHolder) {
            label.text = data.title
            category.text = data.category
            price.text = data.price.toString()
            quantity.text = data.quantity.toString()
            image.load(data.image) {
                crossfade(true)
                transformations(RoundedCornersTransformation(70f, 70f, 70f, 70f))
            }
        }
    }
}
