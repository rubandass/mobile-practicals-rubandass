package graysono.com.cp06asynctaskrecyclerview.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.helpers.Album

import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        displayToolbar(true)
        val album: Album? = intent.extras.getParcelable("album")

        txvAlbumName.text = "Album Name:" + album?.name
        txvAlbumArtist.text = "Artist Name:" + album?.artist
        txvAlbumCount.text = "Play Count:" + album?.playCount.toString()

        Picasso.with(this@DetailsActivity).load(album?.image).error(R.drawable.ic_image_black_48dp).placeholder(R.drawable.ic_image_black_48dp).into(imvAlbumImage)
    }
}
