package com.rama.apod.ui

import androidx.lifecycle.*
import com.rama.apod.data.FavItems
import com.rama.apod.data.model.ItemApod
import com.rama.apod.data.model.Photo
import com.rama.apod.domain.Repo
import com.rama.apod.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApodViewModel(private val repo: Repo) : ViewModel() {
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
    lateinit var fav: FavItems
    fun setFavItem(favphoto: FavItems){
        detailsConfigure = "fav"
        fav = favphoto
    }

    //Search Photos
    init {
        setsol("100")
    }

    private val sol = MutableLiveData<String>()
    fun setsol(solgive: String) {
        sol.value = solgive
    }
    val showPhotosList = sol.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(repo.getItemMarsPhotos(it))
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
    fun saveFavorite(photoFav: FavItems){
        viewModelScope.launch {
            repo.insertFavorite(photoFav)
        }
    }
}


class VMFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}