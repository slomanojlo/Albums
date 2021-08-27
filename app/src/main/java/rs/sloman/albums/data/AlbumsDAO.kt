package rs.sloman.albums.data
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumsDAO {

    @Query("SELECT * FROM album")
    fun getLiveDataAlbumsFromDB() : LiveData<List<Album>?>

    @Query("SELECT * FROM album")
    fun getAlbumsFromDB() : List<Album>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(albums : List<Album>) : List<Long>

    @Query("DELETE FROM album")
    fun deleteAll() : Int

    @Transaction
    fun reinsertAlbums(albums: List<Album>?) {
        deleteAll()
        if(albums.isNullOrEmpty()) return
        insertProduct(albums = albums)
    }
}