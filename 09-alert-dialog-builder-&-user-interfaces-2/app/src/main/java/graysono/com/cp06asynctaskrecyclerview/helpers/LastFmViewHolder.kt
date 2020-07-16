package graysono.com.cp06asynctaskrecyclerview.helpers

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import graysono.com.cp06asynctaskrecyclerview.R

class LastFmViewHolder (view: View) : RecyclerView.ViewHolder(view){
    var txvName: TextView = view.findViewById(R.id.txvName)
    var txvPlayCount: TextView = view.findViewById(R.id.txvPlayCount)
    var imvAlbum: CircleImageView = view.findViewById(R.id.imvAlbum)
}