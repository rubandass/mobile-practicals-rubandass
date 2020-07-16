package graysono.com.cp05asynctasklistview.helpers

import android.content.Context
import android.os.AsyncTask
import android.widget.ListView
import graysono.com.cp05asynctasklistview.R
import java.net.URL
import java.security.cert.CertPath

class FeedAsyncTask(private var context: Context, private var listView: ListView): AsyncTask<String, Void, String>(){
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        val feedXMLParser = FeedXMLParser()
        feedXMLParser.parse(result)
        val feedAdapter = FeedAdapter(context, R.layout.list_item, feedXMLParser.feedEntries)
        listView.adapter = feedAdapter
    }

    override fun doInBackground(vararg url: String?): String {
        return try {
            downloadXML(url[0])
        }catch (e: Exception){
            e.printStackTrace().toString()
        }
    }

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }

}
