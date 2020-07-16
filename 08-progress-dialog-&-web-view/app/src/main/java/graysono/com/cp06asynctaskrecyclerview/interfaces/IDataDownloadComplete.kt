package graysono.com.cp06asynctaskrecyclerview.interfaces

import graysono.com.cp06asynctaskrecyclerview.enums.DownloadStatus

interface IDataDownloadComplete {
//    When implemented, gets the status (enum value) of the async task - checks if its completed
    fun onDownloadComplete(data: String, status: DownloadStatus)
}