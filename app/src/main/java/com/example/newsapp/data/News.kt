package com.example.newsapp.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "news_table")
@JsonClass(generateAdapter = true)
data class News(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "article_id")
    @Json(name = "article_id")
    val id: String,

    @ColumnInfo(name="title")
    val title: String?,

    @ColumnInfo(name="description")
    val description: String?,

    @ColumnInfo(name="source")
    @Json(name = "source_id")
    val source: String?,

    @ColumnInfo(name="image_url")
    @Json(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name="language")
    val language: String?

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(source)
        parcel.writeString(imageUrl)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }

}
