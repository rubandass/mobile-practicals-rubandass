package com.jhonr1.sqlite.activities

import android.os.Bundle
import com.jhonr1.sqlite.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        displayToolbar(true)
    }

}






