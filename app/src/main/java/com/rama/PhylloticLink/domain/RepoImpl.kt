package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource

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