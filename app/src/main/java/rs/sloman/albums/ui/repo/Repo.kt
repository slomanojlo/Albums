package rs.sloman.albums.ui.repo

import androidx.lifecycle.LiveData
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.AlbumsDAO
import rs.sloman.albums.ui.network.AlbumsApi
import javax.inject.Inject

class Repo @Inject constructor(
    private val api: AlbumsApi,
    private val albumsDao: AlbumsDAO
) {
    fun getLiveDataAlbumsFromDB(): LiveData<List<Album>?> = albumsDao.getLiveDataAlbumsFromDB()
    fun getAlbumsFromDB() : List<Album>? = albumsDao.getAlbumsFromDB()

    suspend fun updateAlbums() {
        val remoteAlbums = api.getAlbums()
        albumsDao.reinsertAlbums(albums = remoteAlbums)
    }


}