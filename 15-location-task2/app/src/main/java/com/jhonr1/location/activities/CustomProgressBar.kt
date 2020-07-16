package com.jhonr1.location.activities

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.jhonr1.location.R


class CustomProgressBar(context: Context) {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view: View = inflater.inflate(R.layout.progress_bar, null)
    private val dialog: Dialog = Dialog(context, R.style.CustomProgressBarTheme)

    fun show(){
        dialog.setContentView(view)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }
}
