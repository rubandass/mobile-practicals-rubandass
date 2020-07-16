package graysono.com.cp06asynctaskrecyclerview.interfaces

import android.view.View

interface IRecyclerViewItem {
    fun onItemClick(view: View, position: Int)
}