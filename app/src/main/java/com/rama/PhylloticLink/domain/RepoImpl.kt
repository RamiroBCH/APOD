package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.DatasourceImpl
import com.rama.PhylloticLink.data.MarsEntities
import com.rama.PhylloticLink.data.NormalizedItem
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.vo.Resource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val datasource: Datasource):Repo {

    override suspend fun getItemApod(): Resource<ItemApod> {
        return datasource.getApod()
    }
    override suspend fun insertApodToRoom(apodEntities: ApodEntities) {
        return datasource.insertApodToRoom(apodEntities)
    }

    override suspend fun getApodFromRoom(): Resource<ApodEntities> {
        return datasource.getApodFromRoom()
    }

    override suspend fun getItemMarsPhotos(sol: Int): Resource<List<Photo>> {
        return datasource.getMarsPhotoBySol(sol)
    }

    override suspend fun insertMarsToRoom(marsEntities: MarsEntities) {
        return datasource.insertMarsToRoom(marsEntities)
    }



    override suspend fun insertFavorite(photoFav: NormalizedItem) {
        return datasource.insertFav(photoFav)
    }
    override suspend fun getItemsFavorite(): Resource<List<NormalizedItem>> {
        return datasource.getItemsFav()
    }
    override suspend fun deleteFromFavorites(normalizedItem: NormalizedItem) {
        return datasource.deteleFavorite(normalizedItem)
    }
}