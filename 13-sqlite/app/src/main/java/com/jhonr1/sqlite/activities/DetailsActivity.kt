package com.jhonr1.sqlite.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import com.jhonr1.sqlite.R
import com.jhonr1.sqlite.fragments.DetailFragment
import com.jhonr1.sqlite.helpers.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_details.*

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
        album = intent.extras?.getParcelable("album")!!

        txvAlbumName.text = "Album Name:" + album?.name
        txvAlbumArtist.text = "Artist Name:" + album?.artist
        txvAlbumCount.text = "Play Count:" + album?.playCount.toString()
        btnView.setOnClickListener(ViewButtonOnClickListener())
        Picasso.with(this@DetailsActivity).load(album?.image).error(R.drawable.ic_image_black_48dp).placeholder(R.drawable.ic_image_black_48dp).into(imvAlbum)
        progressBar.dismiss()


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






