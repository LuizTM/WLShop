package dev.luiztm.wlshop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.wlshop.R


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

data class NavigationItem(
    @DrawableRes val icon: Int,
    val label: String,
    val route: String
)

class NavigationViewAdapter(
    private val dataSet: List<NavigationItem>,
    private val itemViewClick: (NavigationItem) -> Unit,
) : RecyclerView.Adapter<NavigationViewAdapter.ViewHolder>() {

    private var _selectedPosition = 0
    private var _lastSelectedPosition = 0

    override fun getItemCount() = dataSet.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun selectedItem(view: View, visibility: Int) {
            view.findViewById<Group>(R.id.wlshop_nav_group_selected).visibility = visibility
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.wlshop_bottom_navigation_item_view, viewGroup, false)
        return ViewHolder(view)
    }

    private fun updateItemSelected(position: Int) {
        _lastSelectedPosition = _selectedPosition
        _selectedPosition = position
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun onBackSelection() {
        updateItemSelected(_lastSelectedPosition)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]

        val icon: AppCompatImageView = viewHolder.view.findViewById(R.id.wlshop_nav_img)
        val label: TextView = viewHolder.view.findViewById(R.id.wlshop_nav_label)

        viewHolder.selectedItem(
            viewHolder.view,
            if (_selectedPosition != position) View.GONE else View.VISIBLE
        )

        icon.setImageResource(data.icon)
        label.text = data.label

        viewHolder.itemView.setOnClickListener {
            itemViewClick(data)
            updateItemSelected(position)
        }
    }
}
