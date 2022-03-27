package com.rama.apod.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "APOD")
data class ApodEntities(
    @PrimaryKey(autoGenerate = true)
    val apodId: Int,
    @ColumnInfo
    val date: String,
    @ColumnInfo(name = "details")
    val explanation: String,
    @ColumnInfo
    val hdurl: String,
    @ColumnInfo
    val media_type: String,
    @ColumnInfo
    val service_version: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val url: String,
)

@Entity(tableName = "MARS")
data class MarsEntities(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val earth_date: String,
    @ColumnInfo
    val img_src: String,
    @ColumnInfo
    val sol: Int
)
@Entity(tableName = "FAV")
data class FavItems(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo
    val date:String,
    @ColumnInfo
    val url:String,
    @ColumnInfo
    val details:String

)