package com.jhonr1.localization.interfaces

import android.view.View

interface IRecyclerViewItem {
    fun onItemClick(view: View, position: Int)
}