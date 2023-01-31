package com.example.myapp.repository

import com.example.myapp.data.model.*
import retrofit2.http.*

interface SpotifyService {

    @POST("api_token")
    fun getToken(
        @Body token: Token
    ) : Token

    @GET("albums/{album_id}")
    suspend fun getAlbum(
        @HeaderMap header: Map<String, String>,
        @Path("album_id") albumId: String
    ): Album

    @GET("playlists/{playlist_id}")
    suspend fun getPlayList(
        @HeaderMap header: Map<String, String>,
        @Path("playlist_id") playListId: String
    ): Playlist

    @GET("artists/{artist_id}")
    suspend fun getArtist(
        @HeaderMap header: Map<String, String>,
        @Path("artist_id") artistId: String
    ): Artist

    @GET("albums")
    suspend fun getAlbums(
        @HeaderMap header: Map<String, String>,
        @Query("ids") albumsIds: String,
    ): Result

    @GET("artists")
    suspend fun getArtists(
        @HeaderMap header: Map<String, String>,
        @Query("ids") artistsIds: String
    ): Artists

    @GET("tracks")
    suspend fun getTracks(
        @HeaderMap header: Map<String, String>,
        @Query("ids") tracksIds: String
    ) : Resultado

    @GET("search")
    suspend fun getSearch(
        @HeaderMap header: Map<String, String>,
        @Query("q") search: String,
        @Query("type") tracksArtist: List<String>,
        @Query("include_external") audio :String
    ) :Search

    /**
     * Generics
     */
    @GET("artists")
    suspend fun <T> getRequest(
        @HeaderMap header: Map<String, String>,
        @Query("ids") artistsIds: String
    ): T
}
