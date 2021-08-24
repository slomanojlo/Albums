package rs.sloman.albums.ui.repo

import rs.sloman.albums.data.Album
import rs.sloman.albums.ui.network.AlbumsApi
import javax.inject.Inject

class Repo @Inject constructor(
    private val api: AlbumsApi
) {

    suspend fun getAlbums() :List<Album> = api.getAlbums()

}