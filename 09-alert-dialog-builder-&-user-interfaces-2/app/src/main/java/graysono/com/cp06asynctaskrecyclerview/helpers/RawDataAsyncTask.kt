package graysono.com.cp06asynctaskrecyclerview.helpers

import android.content.Context
import android.os.AsyncTask
import graysono.com.cp06asynctaskrecyclerview.activities.CustomProgressBar
import graysono.com.cp06asynctaskrecyclerview.enums.DownloadStatus
import graysono.com.cp06asynctaskrecyclerview.interfaces.IDataDownloadComplete
import java.lang.Exception
import java.net.URL

class RawDataAsyncTask(private val listener: IDataDownloadComplete, context: Context) :
    AsyncTask<String, Void, String>() {
    private var downloadStatus = DownloadStatus.NONE
    private var progressBar = CustomProgressBar(context)

//    show the progress bar dialog before executing download
    override fun onPreExecute() {
        progressBar.show()
    }

    //    hide the progress bar dialog after downloading finished
    override fun onPostExecute(result: String) {
        progressBar.dismiss()
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg url: String?): String {
        var data = ""
        try {
            downloadStatus = DownloadStatus.OK
            data = downloadXML(url[0])
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }
}


