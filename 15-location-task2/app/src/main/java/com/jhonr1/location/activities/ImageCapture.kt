package com.jhonr1.location.activities

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.jhonr1.location.helpers.Image
import java.io.File

class ImageCapture(private val context: Context) {
    lateinit var imgFile: File
    lateinit var imgUri: Uri

    fun prepare(): Uri {
        imgFile = Image.create()
//        imgUri = FileProvider.getUriForFile(context,
//            BuildConfig.APPLICATION_ID + ".provider", imgFile)

        val imgUri = FileProvider.getUriForFile(context, context.packageName, imgFile)
        return imgUri
    }
}