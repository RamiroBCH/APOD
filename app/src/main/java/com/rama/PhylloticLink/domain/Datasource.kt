package com.rama.PhylloticLink.domain

import androidx.lifecycle.LiveData
import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.MarsEntities
import com.rama.PhylloticLink.data.NormalizedItem
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource

interface Datasource {
    suspend fun getApod(): Resource<ItemApod>
    suspend fun insertApodToRoom(apodEntities: ApodEntities)
    suspend fun getApodFromRoom(): Resource<ApodEntities>
    suspend fun getMarsPhotoBySol(sol: Int): Resource<List<DModels>>
    suspend fun insertFav(photoFav: NormalizedItem)
    suspend fun getItemsFav(): Resource<List<NormalizedItem>>
    suspend fun deteleFavorite(normalizedItem: NormalizedItem)
}