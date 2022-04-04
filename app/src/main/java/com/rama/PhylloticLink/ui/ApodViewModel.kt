package com.rama.PhylloticLink.ui

import androidx.lifecycle.*
import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.MarsEntities
import com.rama.PhylloticLink.data.NormalizedItem
import com.rama.PhylloticLink.data.model.ItemApod
import com.rama.PhylloticLink.data.model.Photo
import com.rama.PhylloticLink.domain.Repo
import com.rama.PhylloticLink.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    //Data Transfer
    lateinit var detailsConfigure: String

    lateinit var photoMars: Photo
    fun setPhoto(photo: Photo) {
        detailsConfigure = "mars"
        photoMars = photo
    }
    lateinit var apod: ItemApod
    fun setAppod(data: ItemApod) {
        detailsConfigure = "apod"
        apod = data
    }
    lateinit var fav: NormalizedItem
    fun setFavItem(favphoto: NormalizedItem){
        detailsConfigure = "fav"
        fav = favphoto
    }



    private val sol = MutableLiveData<Int>()
    fun setSol(solgive: Int) {
        sol.value = solgive
    }
    val showPhotosList = sol.distinctUntilChanged().switchMap {sol ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(repo.getItemMarsPhotos(sol))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
    val getApod = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repo.getItemApod())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getFavItems() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repo.getItemsFavorite())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    //Save Favorites
    fun saveFavorite(photoFav: NormalizedItem){
        viewModelScope.launch {
            repo.insertFavorite(photoFav)
        }
    }
    //Delete Item From Favorites
    fun deleteFavorite(normalizedItem: NormalizedItem){
        viewModelScope.launch {
            repo.deleteFromFavorites(normalizedItem)
        }
    }

    //Insertar datos obtenidos de las apis en Room
    fun insertApodItems(apodEntities: ApodEntities){
        viewModelScope.launch {
            repo.insertApodToRoom(apodEntities)
        }
    }
    fun getApodFromRoom() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repo.getApodFromRoom())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
    fun insertMarsItems(marsEntities: MarsEntities){
        viewModelScope.launch {
            repo.insertMarsToRoom(marsEntities)
        }
    }
}

class VMFactory constructor(private val repo: Repo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }

}