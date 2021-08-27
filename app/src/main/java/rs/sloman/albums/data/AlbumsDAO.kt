package rs.sloman.albums.data
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumsDAO {


    @Query("SELECT * FROM album")
    fun getAlbumsFromDB() : LiveData<List<Album>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(albums : List<Album>) : List<Long>

    @Query("DELETE FROM album")
    fun deleteAll() : Int

    @Transaction
    suspend fun reinsertAlbums(albums: List<Album>?) {
        deleteAll()
        if(albums.isNullOrEmpty()) return
        insertProduct(albums = albums)
    }
}