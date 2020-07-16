package com.jhonr1.location.interfaces

import com.jhonr1.location.enums.DownloadStatus

interface IDataDownloadComplete {
//    When implemented, gets the status (enum value) of the async task - checks if its completed
    fun onDownloadComplete(data: String, status: DownloadStatus)
}