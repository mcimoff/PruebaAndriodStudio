package com.example.myapplication.Entities

import android.os.Parcel
import android.os.Parcelable

class Incidente(param1: String ) : Parcelable {

    var nombre : String

    init {
        this.nombre = param1
    }
    constructor(parcel: Parcel) : this(parcel.readString().toString()) {
    }

    override fun toString(): String {
        return this.nombre;
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Incidente> {
        override fun createFromParcel(parcel: Parcel): Incidente {
            return Incidente(parcel)
        }

        override fun newArray(size: Int): Array<Incidente?> {
            return arrayOfNulls(size)
        }
    }


}