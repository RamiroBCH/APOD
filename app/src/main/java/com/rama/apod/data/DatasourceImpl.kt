package com.rama.apod.data

import com.rama.apod.RoomData
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.Photo
import com.rama.apod.domain.Datasource
import com.rama.apod.vo.Resource
import com.rama.apod.vo.RetrofitClient

class DatasourceImpl(val roomData: RoomData): Datasource {
    val marskey = "y0c2KCbxZDzyUn7wPOW9lZfMdtFLLIr0giMKdxN3"

    override suspend fun getApod():Resource<ItemApod>{
        return Resource.Success(RetrofitClient.apodwebservice.getApod(api_key = marskey))
    }


    override suspend fun getMarsPhotoBySol(sol: Int):Resource<List<Photo>>{
        return Resource.Success(RetrofitClient.webservice.getMarsPhotos(sol,api_key = marskey).photos)
    }


    override suspend fun insertFav(photoFav: FavItems){
        roomData.itemsDao().addFavoritePhoto(photoFav)
    }
    override suspend fun getItemsFav(): Resource<List<FavItems>> {
        return Resource.Success(roomData.itemsDao().getAllFavorites())
    }
    override suspend fun deteleFavorite(favItems: FavItems) {
        roomData.itemsDao().deleteFromFavorites(favItems)
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