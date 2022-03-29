package com.rama.PhylloticLink.data.model

data class ItemApod(
    val date: String = "",
    val explanation: String = "",
    val hdurl: String = "",
    val media_type: String = "",
    val service_version: String = "",
    val title: String = "",
    val url: String = ""
)
data class ItemMarsPhotos(
    val photos: List<Photo>
)

data class Photo(
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val sol: Int
)


