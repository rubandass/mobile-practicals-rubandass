package com.jhonr1.sqlite.interfaces

import android.widget.ImageButton
import com.jhonr1.sqlite.helpers.Playlist

interface IItemClick {
    fun onItemClick(playlist: Playlist, imgBtn: ImageButton)
}