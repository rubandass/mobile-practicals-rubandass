package com.jhonr1.sqlite.helpers

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jhonr1.sqlite.R
import de.hdodenhof.circleimageview.CircleImageView

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txvName: TextView = view.findViewById(R.id.txvPlaylistName)
    var txvCategory: TextView = view.findViewById(R.id.txvPlaylistCategory)
    var txvDescription: TextView = view.findViewById(R.id.txvPlaylistDescription)
    var txvDateTime: TextView = view.findViewById(R.id.txvDate)
    var imgBtnMenu: ImageButton = view.findViewById(R.id.imgBtnPlaylistMenu)
}