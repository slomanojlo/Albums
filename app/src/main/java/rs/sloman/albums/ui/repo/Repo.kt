package rs.sloman.albums.ui.repo

import kotlinx.coroutines.flow.Flow
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.AlbumsDAO
import rs.sloman.albums.ui.network.AlbumsApi
import javax.inject.Inject

class Repo @Inject constructor(
    private val api: AlbumsApi,
    private val albumsDao: AlbumsDAO
) {

    suspend fun getAlbums(): List<Album> = api.getAlbums()

    suspend fun updateAlbums(albums : List<Album>): List<Long> = albumsDao.reinsertAlbums(albums =albums)
    fun getAlbumsFromDB() : Flow<List<Album>> = albumsDao.getAlbumsFromDB()

}