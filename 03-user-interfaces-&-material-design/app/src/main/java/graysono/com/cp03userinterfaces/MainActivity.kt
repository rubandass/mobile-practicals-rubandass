package graysono.com.cp03userinterfaces

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import kotlinx.android.synthetic.main.toast.view.*

import kotlinx.android.synthetic.main.toast.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Alternative syntax for finding a view by its id - this is recommended by Google
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fabYelp = findViewById<View>(R.id.fabYelp)
        fabYelp.setOnClickListener(YelpButtonOnClickListener())

        val fabYoutube = findViewById<View>(R.id.fabYoutube)
        fabYoutube.setOnClickListener(YoutubeButtonOnClickListener())

        val fabGoogle = findViewById<View>(R.id.fabGoogle)
        fabGoogle.setOnClickListener(GoogleButtonOnClickListener())

        val rdGroup = findViewById<RadioGroup>(R.id.rdGroup)
        rdGroup.setOnCheckedChangeListener(RadioButtonOnCheckedChangeListener())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this@MainActivity, "Settings", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class YelpButtonOnClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            // Add appropriate implicit intent here
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yelp.com")))
        }
    }

    inner class YoutubeButtonOnClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            // Add appropriate implicit intent here
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com")))
        }
    }

    inner class GoogleButtonOnClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            // Add appropriate implicit intent here
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
        }
    }

    inner class RadioButtonOnCheckedChangeListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(rg: RadioGroup?, id: Int) {
            // Add custom toast code here
            val radio: RadioButton = findViewById(id)
            val layout = layoutInflater.inflate(R.layout.toast, toast)
            layout.textView_lang.text = radio.text
            if (radio.text == "Go"){
                layout.logo.setImageResource(R.drawable.ic_go_lang)
            }

            if (radio.text == "Java"){
                layout.logo.setImageResource(R.drawable.ic_java)
            }

            if (radio.text == "Swift"){
                layout.logo.setImageResource(R.drawable.ic_swift)
            }
            val myToast = Toast(applicationContext)
            myToast.duration = Toast.LENGTH_SHORT
            myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            myToast.view = layout//setting the view of custom toast layout
            myToast.show()

        }
    }
}

