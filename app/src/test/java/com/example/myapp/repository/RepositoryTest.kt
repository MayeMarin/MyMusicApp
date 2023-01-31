package com.example.myapp.repository

import com.example.myapp.data.model.*
import kotlinx.coroutines.runBlocking
import org.junit.Test


/*
* pruebas de integracion
* */
class RepositoryTest {
    @Test
    fun `api should return playlists`() = runBlocking {
        val playlists = Repository.getPlayList(playListId = "37i9dQZF1DWXRqgorJj26U")
        assert(playlists?.description == "Rock legends & epic songs that continue to inspire generations.")
        assert(playlists?.images?.size == 1)
        assert(playlists?.collaborative == false)
        assert(playlists?.public == true)
        if (playlists != null) {
            assert(playlists.images.first().url.isNotEmpty())
        }
        assert(playlists?.name == "Rock Classics")
        assert(playlists?.href == "https://api.spotify.com/v1/playlists/37i9dQZF1DWXRqgorJj26U")
        assert(playlists?.primaryColor == "#ffffff")
        assert(playlists?.owner == Owner(
            displayName = "Spotify",
            externalUrls = ExternalUrls(spotify = "https://open.spotify.com/user/spotify"),
            uri = "spotify:user:spotify",
            href = "https://api.spotify.com/v1/users/spotify",
            id = "spotify",
            type = "user")
        )
    }

    @Test
    fun `api should return artist`() = runBlocking {
        val artist = Repository.getArtist(artistId = "0TnOYISbd1XYRBk9myaseg")
        assert(artist?.href == "https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg")
        assert(artist?.name == "Pitbull")
        //assert(artist?.popularity == 82)
        //assert(artist?.genres?.size == 5)
        assert(artist?.type == "artist")
        assert(artist?.uri == "spotify:artist:0TnOYISbd1XYRBk9myaseg")
        assert(artist?.externalUrls == ExternalUrls(spotify = "https://open.spotify.com/artist/0TnOYISbd1XYRBk9myaseg"))
        //assert(artist?.followers == Followers (href = null, total = 9387560))
        if (artist != null) {
            assert(artist.images.first().url.isNotEmpty())
        }



    }
    @Test
    fun `api should return album`() = runBlocking {
        val album = Repository.getAlbum(albumId = "4aawyAB9vmqN3uQ7FjRGTy")
        assert(album?.name == null)
        assert(album?.availableMarkets.isNullOrEmpty())
        assert(album?.popularity == null)
        assert(album?.label == null)
        assert(album?.externalUrls == null)
        assert(album?.genres?.size == null)
        //assert(album?.releaseDate == "2012-11-16")
        //assert(album?.releaseDatePrecision == "day")
        //assert(album?.totalTracks == 18)
        assert(album?.albumType == "album")
        assert(album?.name == "Global Warming")
        assert(album?.href == "https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy")

    }
}
