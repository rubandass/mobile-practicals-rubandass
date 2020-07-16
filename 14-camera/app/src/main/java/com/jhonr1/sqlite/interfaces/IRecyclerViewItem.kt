package com.jhonr1.sqlite.interfaces

import android.view.View

interface IRecyclerViewItem {
    fun onItemClick(view: View, position: Int)
}