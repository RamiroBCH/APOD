package com.rama.apod.domain

import com.rama.apod.data.FavItems
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.Photo
import com.rama.apod.vo.Resource

interface Datasource {
    suspend fun getApod(): Resource<ItemApod>
    suspend fun getMarsPhotoBySol(sol: Int): Resource<List<Photo>>
    suspend fun insertFav(photoFav: FavItems)
    suspend fun getItemsFav(): Resource<List<FavItems>>
    suspend fun deteleFavorite(favItems: FavItems)
}