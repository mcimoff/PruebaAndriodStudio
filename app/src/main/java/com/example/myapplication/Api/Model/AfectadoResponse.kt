package com.example.myapplication.Api.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AfectadoResponse(
    @field:SerializedName("email") val email: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AfectadoResponse> {
        override fun createFromParcel(parcel: Parcel): AfectadoResponse {
            return AfectadoResponse(parcel)
        }

        override fun newArray(size: Int): Array<AfectadoResponse?> {
            return arrayOfNulls(size)
        }
    }
}

