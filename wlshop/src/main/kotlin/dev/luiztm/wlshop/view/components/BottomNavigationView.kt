package dev.luiztm.wlshop.view.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.view.adapter.NavigationItem
import dev.luiztm.wlshop.view.adapter.NavigationViewAdapter

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

class BottomNavigationView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val _recyclerView: RecyclerView by lazy { findViewById(R.id.wlshop_rv_bottom_view) }

    init {
        inflate(context, R.layout.wlshop_bottom_navigation_view, this)
    }

    fun setupView(
        items: List<NavigationItem>,
        onClick: (NavigationItem) -> Unit,
    ) {
        _recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        _recyclerView.adapter = NavigationViewAdapter(items, onClick)
    }

    fun setPreviousSelected() {
        (_recyclerView.adapter as? NavigationViewAdapter)?.onBackSelection()
    }
}
