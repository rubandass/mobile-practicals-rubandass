package com.jhonr1.location.interfaces

import android.widget.ImageButton
import com.jhonr1.location.helpers.Playlist

interface IItemClick {
    fun onItemClick(playlist: Playlist, imgBtn: ImageButton)
}