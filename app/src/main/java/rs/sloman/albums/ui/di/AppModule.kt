package rs.sloman.albums.ui.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.sloman.albums.Constants
import rs.sloman.albums.data.AlbumsDB
import rs.sloman.albums.ui.network.AlbumsApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(AlbumsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideAlbumsApi(retrofit: Retrofit): AlbumsApi =
        retrofit.create(AlbumsApi::class.java)


    @Provides
    @Singleton
    fun provideAlbumsDB(@ApplicationContext app : Context) =
        Room.databaseBuilder(app, AlbumsDB::class.java, Constants.ALBUM_DB)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAlbumsDAO(db: AlbumsDB) = db.getAlbumDao()
}