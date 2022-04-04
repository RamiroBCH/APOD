package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.data.MarsEntities
import com.rama.PhylloticLink.data.NormalizedItem
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val datasourceImpl: DatasourceImpl):Repo {

    override suspend fun getItemApod(): Resource<ItemApod> {
        return datasourceImpl.getApod()
    }
    override suspend fun insertApodToRoom(apodEntities: ApodEntities) {
        return datasourceImpl.insertApodToRoom(apodEntities)
    }

    override suspend fun getApodFromRoom(): Resource<ApodEntities> {
        return datasourceImpl.getApodFromRoom()
    }

    override suspend fun getItemMarsPhotos(sol: Int): Resource<List<Photo>> {
        return datasourceImpl.getMarsPhotoBySol(sol)
    }

    override suspend fun insertMarsToRoom(marsEntities: MarsEntities) {
        return datasourceImpl.insertMarsToRoom(marsEntities)
    }



    override suspend fun insertFavorite(photoFav: NormalizedItem) {
        return datasourceImpl.insertFav(photoFav)
    }
    override suspend fun getItemsFavorite(): Resource<List<NormalizedItem>> {
        return datasourceImpl.getItemsFav()
    }
    override suspend fun deleteFromFavorites(normalizedItem: NormalizedItem) {
        return datasourceImpl.deteleFavorite(normalizedItem)
    }
}