package com.rama.apod.domain

import com.rama.apod.data.Datasource
import com.rama.apod.data.FavItems
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.ItemMarsPhotos
import com.rama.apod.data.model.Photo
import com.rama.apod.vo.Resource

class RepoImpl(private val datasource: Datasource):Repo {
    override suspend fun getItemApod(): Resource<ItemApod> {
        return datasource.getApod()
    }
    override suspend fun getItemMarsPhotos(sol: Int): Resource<List<Photo>> {
        return datasource.getMarsPhotoBySol(sol)
    }



    override suspend fun insertFavorite(photoFav: FavItems) {
        return datasource.insertFav(photoFav)
    }
    override suspend fun getItemsFavorite(): Resource<List<FavItems>> {
        return datasource.getItemsFav()
    }
}