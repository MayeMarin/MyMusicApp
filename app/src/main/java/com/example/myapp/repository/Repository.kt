package com.example.myapp.repository

import android.util.Log
import com.example.myapp.application.AppConstants.TOKEN
import com.example.myapp.data.model.*

object Repository {

    // lets create the retrofit service
    private val client = ServiceGenerator.createService(SpotifyService::class.java)

    suspend fun getAlbum(albumId: String): Album? {
        return try {
            val result = client.getAlbum(
                header = mapOf(
                    "Authorization" to "Bearer BQBB0w7GPtq3p1hMjqRmDfGJVR0f-7tNiPbAfosciQU7q2-Ey4CvRJnz00fuqk65XVxLGwyZEt2wfZuKdNOuGPGr-Zr6iZZL5zAjFZOApheCyEHbde0"
                ),
                albumId = albumId
            )
            result
        } catch (error: Throwable) {
            Log.e("getAlbum", "${error.localizedMessage} \n token: BQBB0w7GPtq3p1hMjqRmDfGJVR0f-7tNiPbAfosciQU7q2-Ey4CvRJnz00fuqk65XVxLGwyZEt2wfZuKdNOuGPGr-Zr6iZZL5zAjFZOApheCyEHbde0")
            error.printStackTrace()
            null
        }
    }

    suspend fun getPlayList(playListId: String): Playlist? {
        return try {
            val result = client.getPlayList(
                header = mapOf(
                    "Authorization" to "Bearer $TOKEN",
                ),// todo: come from api later
                playListId = playListId
            )
            result
        } catch (error: Throwable) {
            Log.e("getPlayList", "${error.localizedMessage}")
            error.printStackTrace()
            null
        }
    }
    suspend fun getArtist(artistId: String): Artist? {
        return try {
            val result = client.getArtist(
                header = mapOf(
                    "Authorization" to  "Bearer $TOKEN"
                ),
                artistId = artistId
            )
            result
        } catch (error: Throwable) {
            null
        }
    }
    suspend fun getAlbums(albumsIds: String): Result? {
        return try {
            val result = client.getAlbums(
                header = mapOf(
                    "Authorization" to "Bearer $TOKEN"
                ),
                albumsIds = albumsIds

            )
            result
        }catch (error: Throwable) {
            Log.e("getAlbums", "${error.localizedMessage} \n token: BQBB0w7GPtq3p1hMjqRmDfGJVR0f-7tNiPbAfosciQU7q2-Ey4CvRJnz00fuqk65XVxLGwyZEt2wfZuKdNOuGPGr-Zr6iZZL5zAjFZOApheCyEHbde0")
            error.printStackTrace()
            null
        }
    }
    suspend fun getArtists(artistsIds: String): Artists? {
        return try {
            val result = client.getArtists(
                header = mapOf(
                    "Authorization" to "Bearer $TOKEN"
                ),
                artistsIds = artistsIds
            )
            result
        }catch (error: Throwable) {
            null
        }
    }
    suspend fun getSearch(search: String,tracksArtist: List<String>, audio: String ): Search? {
        return try {
            val result = client.getSearch(
                header = mapOf(
                    "Authorization" to "Bearer $TOKEN"
                ),
                search = search,
                tracksArtist = tracksArtist,
                audio = audio
            )
            result
        }catch (error: Throwable) {
            null
        }
    }
    suspend fun getTracks(tracksIds: String): Resultado? {
        return try {
            val result = client.getTracks(
                header = mapOf(
                    "Authorization" to "Bearer $TOKEN"
                ),
                tracksIds = tracksIds
            )
            result
        }catch (error: Throwable) {
            null
        }
    }
}