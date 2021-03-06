package graysono.com.cp06asynctaskrecyclerview.helpers

import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import graysono.com.cp06asynctaskrecyclerview.R

class LastFmRecyclerViewAdapter(private var albums: ArrayList<Album>) :
    RecyclerView.Adapter<LastFmViewHolder>() {
    override fun getItemCount(): Int {
        return if (albums.isNotEmpty()) albums.size else 0
    }

    fun loadNewData(newAlbums: ArrayList<Album>) {
        albums = newAlbums
        notifyDataSetChanged() //Observable method call
    }

    override fun onBindViewHolder(viewHolder: LastFmViewHolder, position: Int) {
        val album: Album = albums[position]
        Picasso.with(viewHolder.imvAlbum.context).load(album.image)
            .placeholder(R.drawable.ic_image_black_48dp).error(R.drawable.ic_image_black_48dp)
            .into(viewHolder.imvAlbum)

        viewHolder.txvName.text = album.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastFmViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return LastFmViewHolder(view)
    }
}