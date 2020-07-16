package com.jhonr1.location.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.jhonr1.location.R
import com.jhonr1.location.interfaces.IItemClick
import com.jhonr1.location.helpers.DBHelper.DateTime

class PlaylistRecyclerViewAdapter(var listener: IItemClick, private var playlists: ArrayList<Playlist>) :
    RecyclerView.Adapter<PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_playlist_list_item, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  if (playlists.isNotEmpty()) playlists.size else 0
    }

    fun notifyData(newPlaylists: ArrayList<Playlist>){
        playlists = newPlaylists
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: PlaylistViewHolder, position: Int) {
        val playlist: Playlist = playlists[position]
        viewHolder.txvName.text = "Name: " + playlist.name
        viewHolder.txvCategory.text = "Category: " + playlist.category
        viewHolder.txvDescription.text = "Description: " + playlist.description
        viewHolder.txvDateTime.text = "Date: " + DateTime.formatDateTime(playlist.dateTime!!)
        viewHolder.imgBtnMenu.setOnClickListener(MenuOnButtonClickListener(playlist, viewHolder.imgBtnMenu))
    }

    inner class MenuOnButtonClickListener(var playlist: Playlist, var imgBtn: ImageButton) : View.OnClickListener{
        override fun onClick(v: View?) {
            listener.onItemClick(playlist, imgBtn)
        }
    }
}