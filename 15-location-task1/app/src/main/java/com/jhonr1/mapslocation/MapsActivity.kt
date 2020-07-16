package com.jhonr1.mapslocation

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapsActivity)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val data:ArrayList<MapsData> = arrayListOf(
            MapsData("Sunset Sound", LatLng(34.0977818, -118.3349175), R.drawable.sunset_sound, "https://www.sunsetsound.com/"),
            MapsData("Mix Recording Studio", LatLng(34.0628235,-118.2835217), R.drawable.mix_recording_studio, "https://mixrecordingstudio.com/"),
            MapsData("Silvelake Recording Studio", LatLng(34.0898534,-118.2838647), R.drawable.silverlake_recording_studio, "http://silverlakerecordingstudios.com/"),
            MapsData("Blue West Recording Studios", LatLng(34.1019772,-118.3327732), R.drawable.blue_west_recording_studio, "https://www.bluerecorders.com/"),
            MapsData("Capitol Studios & Mastering", LatLng(34.103624,-118.3266696), R.drawable.capitol_studios_mastering, "https://www.capitolstudios.com/"),
            MapsData("Sunset Gower Studios", LatLng(34.0967701,-118.3242501), R.drawable.sunset_gower_studios, "https://www.hppsunsetstudios.com/"),
            MapsData("Studio Instrument Rentals", LatLng(34.0971058,-118.3377078), R.drawable.studio_instrument_rentals, "sir-usa.com"),
            MapsData("Paramount Recording Studio", LatLng(34.0978779,-118.3436723), R.drawable.paramount_recording_studio, "http://paramountrecording.com/"),
            MapsData("MelodyGun Sound Studios", LatLng(34.0925607,-118.3253076), R.drawable.melodygun_group, "melodygun.com"),
            MapsData("Playhouse Hollywood", LatLng(34.1003873,-118.3337599), R.drawable.playhouse_hollywood, "http://playhousenightclub.com/"),
            MapsData("ArcLight Cinemas - Hollywood", LatLng(34.0976579,-118.3286704), R.drawable.arc_light, "https://www.arclightcinemas.com/"),
            MapsData("Sunset Las Palmas Studios", LatLng(34.0897832,-118.337696), R.drawable.sunset_gower_studios, "https://www.hppsunsetstudios.com/"),
            MapsData("Burnside Recording Studios", LatLng(34.1015714,-118.3295202), R.drawable.burnside_recording_studios, "https://losangelesvoicerecording.com//")
        )
		
        val zoomLevel = 15f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data[1].location, zoomLevel))

        for (d: MapsData in data) {
            map.addMarker(
                MarkerOptions().position(d.location).title(d.name).snippet(d.url)
            )
            val overlaySize = 100f
            val googleOverlay: GroundOverlayOptions = GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(d.drawable))
                .position(d.location, overlaySize)
            map.addGroundOverlay(googleOverlay)
        }

        setMapLongClick(map)
        setPointOfInterest(map)
        setMapStyle(map)
        enableMyLocation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.normal_map -> map.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid_map -> map.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite_map -> map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain_map -> map.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
        return true
    }

    private fun setMapLongClick(map: GoogleMap){
        map.setOnMapLongClickListener{ latLng ->
            val snippet: String =
                "Latitude: " + latLng.latitude + ", " + "Longitude: " + latLng.longitude
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    private fun setPointOfInterest(map: GoogleMap){
        map.setOnPoiClickListener { poi ->
            val poiMarker: Marker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )
            poiMarker.showInfoWindow()
        }
    }

    private fun setMapStyle(map: GoogleMap){
        try {
            val success: Boolean = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            if (!success){
                Toast.makeText(this@MapsActivity, "Error loading map style", Toast.LENGTH_LONG)
                    .show()
            }
        } catch (e: Resources.NotFoundException) {
            Toast.makeText(this@MapsActivity, "Map style not found", Toast.LENGTH_LONG).show()
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@MapsActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        const val REQUEST_CODE_PERMISSION = 1
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    private fun enableMyLocation(){
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this@MapsActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }
}
