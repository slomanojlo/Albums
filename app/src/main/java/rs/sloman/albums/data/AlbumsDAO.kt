package rs.sloman.albums.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDAO {


    @Query("SELECT * FROM album")
    fun getAlbumsFromDB() : Flow<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(albums : List<Album>) : List<Long>

    @Query("DELETE FROM album")
    fun deleteAll() : Int

    @Transaction
    suspend fun reinsertAlbums(albums: List<Album>) : List<Long>{
        deleteAll()
        return insertProduct(albums = albums)
    }
}