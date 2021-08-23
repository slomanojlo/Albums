package rs.sloman.albums.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.sloman.albums.ui.repo.Repo
import rs.sloman.albums.ui.repo.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepo() : Repository = Repo()

}