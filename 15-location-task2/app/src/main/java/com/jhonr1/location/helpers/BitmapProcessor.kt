package com.jhonr1.location.helpers

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable

object BitmapProcessor {
    fun process(imageFile: String): Bitmap {
        val photoBitmap: Bitmap = BitmapFactory.decodeFile(imageFile)
        return scale(photoBitmap)
    }

    private fun scale(bitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(90F)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true)
    }

    fun convertBitmapToDrawable(resources: Resources, bitmap: Bitmap): BitmapDrawable {
        return BitmapDrawable(resources, bitmap)
    }
}