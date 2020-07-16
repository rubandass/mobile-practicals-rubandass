package com.jhonr1.location.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import com.jhonr1.location.R
import com.jhonr1.location.helpers.BitmapProcessor
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : BaseActivity() {

    private lateinit var imgCapture: ImageCapture
    private lateinit var imvProfile: CircleImageView
    private lateinit var imgBtnProfile: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        displayToolbar(true)
        imgCapture = ImageCapture(this@ProfileActivity)
        imvProfile = findViewById(R.id.imvProfile)
        Picasso.with(this@ProfileActivity).load(R.drawable.user_profile)
            .error(R.drawable.ic_image_black_48dp)
            .placeholder(R.drawable.ic_image_black_48dp)
            .into(imvProfile)
        imgBtnProfile = findViewById(R.id.imgBtnProfile)
        imgBtnProfile.setOnClickListener{openCamera()}

        requestPermissions()
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            this@ProfileActivity,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            REQUEST_CODE_PERMISSION
        )
    }

    private fun isPermissionGranted(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this@ProfileActivity, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this@ProfileActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }

    private fun openCamera(){
        if (isPermissionGranted()) {
            val uri: Uri = imgCapture.prepare()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newIntent: Intent = intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(newIntent, REQUEST_CODE_PERMISSION)
        } else {
            requestPermissions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PERMISSION && resultCode == RESULT_OK){
            val filePath: String = imgCapture.imgFile.path
            val bitmap: Bitmap = BitmapProcessor.process(filePath)
            val bitmapDrawable: BitmapDrawable =
                BitmapProcessor.convertBitmapToDrawable(resources, bitmap)
            imvProfile.setImageDrawable(bitmapDrawable)
        }
    }

    companion object{
        const val REQUEST_CODE_PERMISSION = 1
    }

}
