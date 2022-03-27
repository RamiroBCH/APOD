package com.rama.apod.domain

import com.rama.apod.data.FavItems
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.Photo
import com.rama.apod.vo.Resource

interface Repo {
    suspend fun getItemApod():Resource<ItemApod>
    suspend fun getItemMarsPhotos(sol: Int):Resource<List<Photo>>


    suspend fun insertFavorite(photoFav: FavItems)
    suspend fun getItemsFavorite():Resource<List<FavItems>>
}