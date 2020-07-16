package graysono.com.cp06asynctaskrecyclerview.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.squareup.picasso.Picasso
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.helpers.Album

import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*

class DetailsActivity : BaseActivity() {
    private lateinit var progressBar: CustomProgressBar
    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        displayToolbar(true)

        progressBar = CustomProgressBar(this@DetailsActivity)
        album = intent.extras.getParcelable("album")

        wbv.settings.javaScriptEnabled = true
        wbv.loadUrl(album.url)
        wbv.webViewClient = webViewClient

        progressBar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_main->{
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            R.id.action_about->{
                val aboutUsAlertDialog = CustomAlertDialog(this@DetailsActivity, R.layout.about_us)
                aboutUsAlertDialog.show("About Us")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

        private val webViewClient: WebViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                txvAlbumName.text = "Album Name:" + album?.name
                txvAlbumArtist.text = "Artist Name:" + album?.artist
                txvAlbumCount.text = "Play Count:" + album?.playCount.toString()
                txvUrl.text = url

                Picasso.with(this@DetailsActivity).load(album?.image).error(R.drawable.ic_image_black_48dp).placeholder(R.drawable.ic_image_black_48dp).into(imvAlbum)
                progressBar.dismiss()
            }
        }
}
