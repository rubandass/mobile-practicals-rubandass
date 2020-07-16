package com.jhonr1.localization.activities

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.jhonr1.localization.R

class CustomAlertDialog(private val context: Context, layout: Int) {

    private lateinit var alertDialog: AlertDialog
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view: View = inflater.inflate(layout,null)
    private val builder = AlertDialog.Builder(context)

    fun show(title: String){
        builder.setTitle(title)
        builder.setIcon(R.mipmap.ic_toolbar_logo)
        builder.setCancelable(true)
        builder.setNegativeButton("Ok",DialogInterface.OnClickListener{
                dialog, id -> dialog.cancel()
        })
        alertDialog = builder.setView(view).create()
        builder.show()
    }
}
