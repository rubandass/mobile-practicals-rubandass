package com.jhonr1.location.helpers

import android.view.View
import android.widget.TextView
import com.jhonr1.location.R
import de.hdodenhof.circleimageview.CircleImageView

class LastFmViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
    var txvName: TextView = view.findViewById(R.id.txvName)
    var txvPlayCount: TextView = view.findViewById(R.id.txvPlayCount)
    var imvAlbum: CircleImageView = view.findViewById(R.id.imvAlbum)
}