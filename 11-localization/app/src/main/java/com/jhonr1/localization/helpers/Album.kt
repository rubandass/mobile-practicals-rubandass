package com.jhonr1.localization.helpers

import android.os.Parcel
import android.os.Parcelable

data class Album(
    var name: String?,
    var playCount: Int,
    var artist: String?,
    var image: String?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )

    override fun toString(): String {
        return "Album(name='$name', playCount=$playCount, artist='$artist', image='$image')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(playCount)
        parcel.writeString(artist)
        parcel.writeString(image)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }
}