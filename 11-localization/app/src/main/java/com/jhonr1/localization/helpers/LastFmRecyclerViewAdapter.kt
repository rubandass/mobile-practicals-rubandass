package com.jhonr1.localization.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonr1.localization.R
import com.squareup.picasso.Picasso

class LastFmRecyclerViewAdapter(private var albums: ArrayList<Album>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<LastFmViewHolder>() {
    override fun getItemCount(): Int {
        return if (albums.isNotEmpty()) albums.size else 0
    }

    fun loadNewData(newAlbums: ArrayList<Album>) {
        albums = newAlbums
        notifyDataSetChanged() //Observable method call
    }

    fun getAlbum( position: Int ): Album {
        return albums[position]
    }

    override fun onBindViewHolder(viewHolder: LastFmViewHolder, position: Int) {
        val album: Album = albums[position]
        Picasso.with(viewHolder.imvAlbum.context).load(album.image)
            .placeholder(R.drawable.ic_image_black_48dp).error(R.drawable.ic_image_black_48dp)
            .into(viewHolder.imvAlbum)

        viewHolder.txvName.text = "Album Name: " + album.name
        viewHolder.txvPlayCount.text = "Play Count: " + album.playCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastFmViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return LastFmViewHolder(view)
    }


}