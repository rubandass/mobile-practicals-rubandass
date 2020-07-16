package com.jhonr1.localization.interfaces

import com.jhonr1.localization.helpers.Album
import java.lang.Exception

interface IDataDownloadAvailable {
//    When implemented, loads the available data from the Album array list into the recycler view
    fun onDataAvailable(data: ArrayList<Album>)
    fun onError(e: Exception)
}