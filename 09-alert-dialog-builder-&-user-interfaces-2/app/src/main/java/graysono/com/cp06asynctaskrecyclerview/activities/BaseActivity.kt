package graysono.com.cp06asynctaskrecyclerview.activities

import android.annotation.SuppressLint
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
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

