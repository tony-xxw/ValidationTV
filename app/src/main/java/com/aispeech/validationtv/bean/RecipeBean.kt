package com.aispeech.validationtv.bean

import android.os.Parcel
import android.os.Parcelable

data class RecipeBean(
    var cook_rate: Double?,
    var cookstep: List<Cookstep?>?,
    var dish: String?,
    var image: String?,
    var major: List<Major?>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.createTypedArrayList(Cookstep),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Major)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(cook_rate)
        parcel.writeTypedList(cookstep)
        parcel.writeString(dish)
        parcel.writeString(image)
        parcel.writeTypedList(major)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeBean> {
        override fun createFromParcel(parcel: Parcel): RecipeBean {
            return RecipeBean(parcel)
        }

        override fun newArray(size: Int): Array<RecipeBean?> {
            return arrayOfNulls(size)
        }
    }
}

data class Cookstep(
    var content: String?,
    var image: String?,
    var position: Int?,
    var thumb: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeString(image)
        parcel.writeValue(position)
        parcel.writeString(thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cookstep> {
        override fun createFromParcel(parcel: Parcel): Cookstep {
            return Cookstep(parcel)
        }

        override fun newArray(size: Int): Array<Cookstep?> {
            return arrayOfNulls(size)
        }
    }
}

data class Major(
    var note: String?,
    var title: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(note)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Major> {
        override fun createFromParcel(parcel: Parcel): Major {
            return Major(parcel)
        }

        override fun newArray(size: Int): Array<Major?> {
            return arrayOfNulls(size)
        }
    }
}

