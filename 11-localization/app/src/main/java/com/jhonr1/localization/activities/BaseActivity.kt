package com.jhonr1.localization.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.jhonr1.localization.R
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
    internal fun displayToolbar(enableHome: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
        toolbar.setLogo(R.mipmap.ic_toolbar_logo)
    }
}

