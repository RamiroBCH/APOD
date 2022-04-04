package com.rama.PhylloticLink.data

import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.domain.ApodWebService
import com.rama.PhylloticLink.domain.Datasource
import com.rama.PhylloticLink.domain.MarsWebService
import com.rama.PhylloticLink.vo.Resource
import javax.inject.Inject

class DatasourceImpl @Inject constructor(
    val itemsDao: ItemsDao,
    val apod: ApodWebService,
    val mars: MarsWebService): Datasource {

    val marskey = "y0c2KCbxZDzyUn7wPOW9lZfMdtFLLIr0giMKdxN3"
    //Metodos que usan Retrofit
    override suspend fun getApod():Resource<ItemApod>{
        return Resource.Success(apod.getApod(api_key = marskey))
    }
    override suspend fun getMarsPhotoBySol(sol: Int):Resource<List<Photo>>{
        return Resource.Success(mars.getMarsPhotos(sol,api_key = marskey).photos)
    }
    //Metodos que usan Room
    override suspend fun insertFav(photoFav: NormalizedItem){
        itemsDao.addFavoritePhoto(photoFav)
    }
    override suspend fun getItemsFav(): Resource<List<NormalizedItem>> {
        return Resource.Success(itemsDao.getAllFavorites())
    }
    override suspend fun deteleFavorite(normalizedItem: NormalizedItem) {
        itemsDao.deleteFromFavorites(normalizedItem)
    }
    //Metodos que usan Room para salvar datos obtenidos con Retrofit
    override suspend fun insertApodToRoom(apodEntities: ApodEntities) {
        itemsDao.insertApodToRoom(apodEntities)
    }
    override suspend fun insertMarsToRoom(marsEntities: MarsEntities) {
        itemsDao.insertMarsToRoom(marsEntities)
    }
    //Metodos que usan Room para obtener datos
    override suspend fun getApodFromRoom(): Resource<ApodEntities>{
        return Resource.Success(itemsDao.getAllApod())
    }
}

/*val generatePhotosList = Resource.Success(
        listOf(
            Photo("2012-08-16", 3132, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00010/soas/rdr/ccam/CR0_398380645PRCLF0030000CCAM04010L1.PNG", 10),
            Photo("2012-08-16", 58870, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00010/opgs/edr/ccam/CR0_398381687EDR_F0030000CCAM05010M_.JPG", 10),
            Photo("2012-08-16", 58871, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00010/opgs/edr/ccam/CR0_398381577EDR_F0030000CCAM05010M_.JPG", 10),
            Photo("2012-08-16", 58873, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00010/opgs/edr/ccam/CR0_398381359EDR_F0030000CCAM05010M_.JPG", 10)
        )
    )*/