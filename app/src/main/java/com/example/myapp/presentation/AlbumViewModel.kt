package com.example.myapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.myapp.core.Resource
import com.example.myapp.data.model.Albums
import com.example.myapp.data.model.Artist
import com.example.myapp.data.model.Tracks
import com.example.myapp.repository.Repository
import kotlinx.coroutines.Dispatchers

class AlbumViewModel(private val repo: Repository): ViewModel() {
    
    var albumsResponse: List<Albums> = listOf()

    fun fetchUpAlbums() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = repo.getAlbums(albumsIds = "2guirTSEqLizK7j9i1MTTZ,6s84u2TUpR3wdUv4NgKA2j,382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc,70lQYZtypdCALtFVlQAcvx,28yHV3Gdg30AiB8h8em1eW,0bCAjiUamIFqKJsekOYuRw,6mUdeDZCsExyJLMdAfDuwh")
            albumsResponse = result?.albums ?: emptyList()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }
    }

    var artistsResponse: List<Artist> = listOf()

    fun fetchUpArtists() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val results = repo.getArtists(artistsIds = "5a2EaR3hamoenG9rDuVn8j,57dN52uHvrHOxijzpIgu3E,0TnOYISbd1XYRBk9myaseg,1vCWHaC5f2uS3yhpwWbIA6,7Ey4PD4MYsKc5I2dolUwbH,07XSN3sPlIlB2L2XNcTwJw,0k17h0D3J5VfsdmQ1iZtE9,12Chz98pHFMPJEknJQMWvI,0kbYTNQb4Pb1rPbbaF0pT4")
            artistsResponse = results?.artists ?: emptyList()
            emit(Resource.Success(results))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }

    }

    var tracksResponse: List<Tracks> = listOf()

    fun fetchTracks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val results = repo.getTracks(tracksIds = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,4lteJuSjb9Jt9W1W7PIU2U,2takcwOaAZWiXQijPHIx7B,5ghIJDpPoe3CfHMGu71E6T,6SRsiMl7w1USE4mFqrOhHC,5wANPM4fQCJwkGd4rN57mH")
            tracksResponse = results?.tracks ?: emptyList()
            emit(Resource.Success(results))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }

    }
    fun quickSearch() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getSearch(search = "remaster%20track:Doxy%20artist:Miles%20Davis", tracksArtist = listOf("track,artist"), audio = "audio")))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }

    }


    class AlbumViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Repository::class.java).newInstance(repo)
        }
    }
}
