package com.shivamsingh.blog_lite.ui.model

import android.os.Parcel
import android.os.Parcelable

data class PostEntity(val id: Int,
                      val title: String,
                      val body: String,
                      val email: String,
                      val avatar: String,
                      val commentsCount: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeString(email)
        parcel.writeString(avatar)
        parcel.writeInt(commentsCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostEntity> {
        override fun createFromParcel(parcel: Parcel): PostEntity {
            return PostEntity(parcel)
        }

        override fun newArray(size: Int): Array<PostEntity?> {
            return arrayOfNulls(size)
        }
    }
}