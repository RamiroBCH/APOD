package com.rama.PhylloticLink.data.model

import com.rama.PhylloticLink.data.MarsEntities
import com.rama.PhylloticLink.domain.DModels


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


fun ItemMarsPhotos.asMarsEntities(): List<MarsEntities>{
    return photos.map{
        MarsEntities(
            id = it.id,
            earth_date = it.earth_date,
            img_src = it.img_src,
            sol = it.sol
        )
    }
}


