package rs.sloman.albums.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.AlbumsDAO
import rs.sloman.albums.data.AlbumsDB

@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: AlbumsDB
    private lateinit var dao: AlbumsDAO
    private val fakeAlbum = Album(0, "Title").apply {
        id = 1
    }

    @Before
    fun setup() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AlbumsDB::class.java
            )
            .allowMainThreadQueries()
            .build()

        dao = database.getAlbumDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAlbum() = runBlocking {

        dao.insertProduct(listOf(fakeAlbum))
        val allProducts = dao.getAlbumsFromDB()

        assertThat(allProducts).contains(fakeAlbum)
    }

    @Test
    fun deleteAlbumItem() = runBlocking {

        dao.insertProduct(listOf(fakeAlbum))
        dao.deleteAll()

        val allProducts = dao.getAlbumsFromDB()

        assertThat(allProducts).doesNotContain(fakeAlbum)
    }

    @Test
    fun reinsertAlbumItems()= runBlocking {

        dao.insertProduct(listOf(fakeAlbum))
        dao.reinsertAlbums(listOf(fakeAlbum))

        val allProducts = dao.getAlbumsFromDB()

        assertThat(allProducts).contains(fakeAlbum)
    }

    @Test
    fun getLiveDataAlbums() = runBlocking {

        dao.insertProduct(listOf(fakeAlbum))
        val allProducts = dao.getLiveDataAlbumsFromDB().getOrAwaitValue()

        assertThat(allProducts).contains(fakeAlbum)

    }

}