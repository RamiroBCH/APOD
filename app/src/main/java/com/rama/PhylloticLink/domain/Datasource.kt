package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource

interface Datasource {
    suspend fun getApod(): Resource<ItemApod>
    suspend fun getMarsPhotoBySol(sol: Int): Resource<List<Photo>>
    suspend fun insertFav(photoFav: FavItems)
    suspend fun getItemsFav(): Resource<List<FavItems>>
    suspend fun deteleFavorite(favItems: FavItems)
}