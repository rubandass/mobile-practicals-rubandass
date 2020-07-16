package graysono.com.cp06asynctaskrecyclerview.activities

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import graysono.com.cp06asynctaskrecyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
    internal fun displayToolbar(enableHome: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
        toolbar.setLogo(R.mipmap.ic_toolbar_logo)
    }
}

