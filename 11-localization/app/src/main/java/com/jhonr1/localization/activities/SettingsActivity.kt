package com.jhonr1.localization.activities

import android.os.Bundle
import com.jhonr1.localization.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_details)
        displayToolbar(true)
    }

}






