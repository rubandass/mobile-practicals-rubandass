package graysono.com.cp06asynctaskrecyclerview.interfaces

import graysono.com.cp06asynctaskrecyclerview.helpers.Album
import java.lang.Exception

interface IDataDownloadAvailable {
//    When implemented, loads the available data from the Album array list into the recycler view
    fun onDataAvailable(data: ArrayList<Album>)
    fun onError(e: Exception)
}