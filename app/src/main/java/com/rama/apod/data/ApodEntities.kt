package com.rama.apod.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAV")
data class FavItems(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val date:String,
    @ColumnInfo
    val url:String,
    @ColumnInfo
    val details:String

)