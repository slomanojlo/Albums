package rs.sloman.albums.ui.network

import retrofit2.http.GET
import rs.sloman.albums.data.Album

interface AlbumsApi {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("albums")
    suspend fun getAlbums() : List<Album>?
}