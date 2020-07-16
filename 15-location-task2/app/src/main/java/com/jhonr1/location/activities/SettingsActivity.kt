package com.jhonr1.location.activities

import android.os.Bundle
import com.jhonr1.location.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        displayToolbar(true)
    }

}






