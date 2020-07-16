package com.jhonr1.location.helpers

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MapsData (
    var name: String,
    var location: LatLng,
    var drawable: Int,
    var url: String
) : ClusterItem {
    override fun getSnippet(): String? {
        return url
    }

    override fun getTitle(): String? {
        return name
    }

    override fun getPosition(): LatLng {
        return location
    }

}