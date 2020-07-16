package com.jhonr1.sqlite.activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.jhonr1.sqlite.R
import com.jhonr1.sqlite.interfaces.IDataReceived
import com.jhonr1.sqlite.enums.DownloadStatus
import com.jhonr1.sqlite.helpers.*
import com.jhonr1.sqlite.interfaces.IDataDownloadAvailable
import com.jhonr1.sqlite.interfaces.IDataDownloadComplete
import com.jhonr1.sqlite.interfaces.IRecyclerViewItem
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.custom_toast.*

class MainActivity : BaseActivity(), IDataDownloadAvailable,
    IDataDownloadComplete, IRecyclerViewItem, IDataReceived {

    private lateinit var rawDataAsyncTask: RawDataAsyncTask
    private lateinit var lastFmRecyclerViewAdapter: LastFmRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayToolbar(false)

        lastFmRecyclerViewAdapter = LastFmRecyclerViewAdapter(ArrayList())
        revAlbums.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        revAlbums.addOnItemTouchListener((RecyclerItemOnClickListener(this@MainActivity, revAlbums,this)))
        revAlbums.adapter = lastFmRecyclerViewAdapter

        val url: String = createURI(getString(R.string.base_url), getString(R.string.method),
            "beyonce", getString(R.string.api_key), getString(R.string.format))
        rawDataAsyncTask = RawDataAsyncTask(this, this@MainActivity)
        rawDataAsyncTask.execute(url)
    }

    private fun createURI(
        baseURL: String, method: String, artist: String,
        apiKey: String, format: String
    ): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("method", method)
            .appendQueryParameter("artist", artist)
            .appendQueryParameter("api_key", apiKey)
            .appendQueryParameter("format", format)
            .build().toString()
    }

    override fun onDataAvailable(data: ArrayList<Album>) {
        Log.d(getString(R.string.TAG), getString(R.string.on_data_available, data))
        lastFmRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(e: Exception) {
        Log.d(getString(R.string.TAG), getString(R.string.on_error, e.message))
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val lastFmAsyncTask = LastFmAsyncTask(this)
            lastFmAsyncTask.execute(data)
        }
    }

    override fun onDataReceived(data: String) {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(
            R.layout.custom_toast,
            findViewById(R.id.layoutRateUs)
        )
        val toast = Toast(this@MainActivity)
        val toastTextView = layout.findViewById(R.id.txvRateUsToast) as TextView
        toastTextView.text = data
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }

    override fun onItemClick(view: View, position: Int){
        val album: Album? = lastFmRecyclerViewAdapter.getAlbum(position)
        if(album != null){
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("album", album)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    fun showDialog(){
        val dialogFragment = RateUsDialogFragment(this)
        dialogFragment.show(supportFragmentManager, null)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_main->{
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                true
            }

            R.id.action_playlist->{
                startActivity(Intent(this@MainActivity, PlaylistActivity::class.java))
                true
            }

            R.id.action_about->{
                val aboutUsAlertDialog = CustomAlertDialog(this@MainActivity, R.layout.about_us)
                aboutUsAlertDialog.show("About Us")
                true
            }
            R.id.action_rateus->{
                showDialog()
                true
            }
            R.id.action_settings->{
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val queryResult: String? = sharedPref.getString("album_query", "")

        if (queryResult!!.isNotEmpty()){
            val url: String = createURI(getString(R.string.base_url), getString(R.string.method),
                queryResult, getString(R.string.api_key), getString(R.string.format))
            rawDataAsyncTask = RawDataAsyncTask(this, this@MainActivity)
            rawDataAsyncTask.execute(url)
        }
    }
}

