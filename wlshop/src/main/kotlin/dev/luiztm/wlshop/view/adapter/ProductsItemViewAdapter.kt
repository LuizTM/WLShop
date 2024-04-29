package dev.luiztm.wlshop.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import dev.luiztm.wlshop.view.components.ProductsItemView


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
class ProductsItemViewAdapter(
    private val dataSet: List<ProductsResponseItem>,
    private val itemViewClick: (ProductsResponseItem) -> Unit
) : RecyclerView.Adapter<ProductsItemViewAdapter.ViewHolder>() {
    override fun getItemCount() = dataSet.size

    class ViewHolder(val view: ProductsItemView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductsItemView(viewGroup.context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]

        viewHolder.view.setupView(
            name = data.title,
            price = data.price.toString(),
            image = data.image,
            click = { itemViewClick(data) }
        )
    }
}
