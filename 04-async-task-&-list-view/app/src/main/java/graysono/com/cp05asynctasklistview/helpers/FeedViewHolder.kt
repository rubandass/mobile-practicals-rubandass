package graysono.com.cp05asynctasklistview.helpers

import android.view.View
import android.widget.TextView
import graysono.com.cp05asynctasklistview.R
import kotlinx.android.synthetic.main.list_item.view.*

class FeedViewHolder(var view: View){
    var  txvName: TextView = view.findViewById(R.id.txvName)
    var txvReleaseDate: TextView = view.findViewById(R.id.txvReleaseDate)
}