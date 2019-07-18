package com.example.apicallactivity.Dataclasses

import android.os.Parcel
import android.os.Parcelable


data class Pojo(var status: String? = null, var msg: String? = null, var data: List<Datum>? = null)

data class Datum(var mandirId: String? = null,
                 var mandirtag: String? = null,
                 var title: String? = null,
                 var urls: String? = null,
                 var createdat: String? = null)

data class Example(var mandir: String? = null,
                   var shortName: String? = null,
                   var date: String? = null,
                   var fdate: String? = null,
                   var imageFolder: String? = null,
                   var images: List<Image>? = null)

data class Image(var imageloc: String? = null, var imageDimension: String? = null):Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
            return 0
    }
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(imageloc)
        dest!!.writeString(imageDimension)
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}
