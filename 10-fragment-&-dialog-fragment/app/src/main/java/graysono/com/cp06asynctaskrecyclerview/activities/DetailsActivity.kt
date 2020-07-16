package graysono.com.cp06asynctaskrecyclerview.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.fragments.DetailFragment
import graysono.com.cp06asynctaskrecyclerview.helpers.Album

import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.web_view.*

class DetailsActivity : BaseActivity() {
    private lateinit var progressBar: CustomProgressBar
    private lateinit var album: Album
    private val fragManager: FragmentManager = supportFragmentManager
    private val detailFrag = DetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        displayToolbar(true)

        progressBar = CustomProgressBar(this@DetailsActivity)
        album = intent.extras.getParcelable("album")

        txvAlbumName.text = "Album Name:" + album?.name
        txvAlbumArtist.text = "Artist Name:" + album?.artist
        txvAlbumCount.text = "Play Count:" + album?.playCount.toString()
        btnView.setOnClickListener(ViewButtonOnClickListener())
        Picasso.with(this@DetailsActivity).load(album?.image).error(R.drawable.ic_image_black_48dp).placeholder(R.drawable.ic_image_black_48dp).into(imvAlbum)
        progressBar.dismiss()
    }

    inner class ViewButtonOnClickListener : View.OnClickListener{

        override fun onClick(v: View) {
            //bundle the url using 'putstring' method and pass it to the fragment class 'arguments'
            val urlBundle = Bundle()
            urlBundle.putString("url", album.url)
            detailFrag.arguments = urlBundle
            //Calling new fragment and replace the existing 'framelayout' to 'detailFragment'
            fragManager.beginTransaction().replace(R.id.frameLayout, detailFrag).commit()
            //Disable 'View' button once 'detailFragment' is loaded
            btnView.isEnabled = false
        }

    }
}






