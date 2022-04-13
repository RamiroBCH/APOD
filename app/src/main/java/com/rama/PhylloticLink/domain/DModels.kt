package com.rama.PhylloticLink.domain

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class DModels(
    val id: Int,
    val date:String,
    val url:String,
    val sol:Int
)