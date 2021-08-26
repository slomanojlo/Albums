package rs.sloman.albums.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class AlbumsDB : RoomDatabase (){

    abstract fun getAlbumDao() : AlbumsDAO
}
