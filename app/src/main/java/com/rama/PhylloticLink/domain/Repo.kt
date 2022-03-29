package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource

interface Repo {
    suspend fun getItemApod():Resource<ItemApod>
    suspend fun getItemMarsPhotos(sol: Int):Resource<List<Photo>>


    suspend fun insertFavorite(photoFav: FavItems)
    suspend fun getItemsFavorite():Resource<List<FavItems>>
    suspend fun deleteFromFavorites(favItems: FavItems)
}