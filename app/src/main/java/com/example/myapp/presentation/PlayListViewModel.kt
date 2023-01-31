package com.example.myapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.myapp.core.Resource
import com.example.myapp.repository.Repository
import kotlinx.coroutines.Dispatchers

class PlayListViewModel(private val repo: Repository): ViewModel() {

    fun fetchUpPlayList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = repo.getPlayList(playListId = "37i9dQZF1DWXRqgorJj26U")
            if (response != null) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Failure("Error obteniendo los datos"))
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }
    }

    class PlayListViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Repository::class.java).newInstance(repo)
        }
    }
}
