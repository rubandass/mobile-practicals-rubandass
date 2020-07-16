package com.jhonr1.sqlite.helpers

import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object Image {
    private lateinit var name: String

    private val timeStamp: String
        get() {
            val outputPattern = "yyyyMMddHHmmss"
            val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
            val currentTime = Date()
            return outputFormat.format(currentTime)
        }

    fun create(): File {
        val rootPath: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val storageDir = File(rootPath, "camera")
        if (!storageDir.exists())
            storageDir.mkdirs()
        name = "img_${timeStamp}.jpg"
        return File(storageDir.path + File.separator + name)
    }
}