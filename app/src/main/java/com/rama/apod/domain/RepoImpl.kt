package com.rama.apod.domain

import com.rama.apod.data.DatasourceImpl
import com.rama.apod.data.FavItems
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.Photo
import com.rama.apod.vo.Resource

class RepoImpl(private val datasourceImpl: DatasourceImpl):Repo {
    override suspend fun getItemApod(): Resource<ItemApod> {
        return datasourceImpl.getApod()
    }
    override suspend fun getItemMarsPhotos(sol: Int): Resource<List<Photo>> {
        return datasourceImpl.getMarsPhotoBySol(sol)
    }



    override suspend fun insertFavorite(photoFav: FavItems) {
        return datasourceImpl.insertFav(photoFav)
    }
    override suspend fun getItemsFavorite(): Resource<List<FavItems>> {
        return datasourceImpl.getItemsFav()
    }

    override suspend fun deleteFromFavorites(favItems: FavItems) {
        return datasourceImpl.deteleFavorite(favItems)
    }
}