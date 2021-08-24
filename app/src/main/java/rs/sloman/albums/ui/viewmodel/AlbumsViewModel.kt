package rs.sloman.albums.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rs.sloman.albums.data.Album
import rs.sloman.albums.ui.repo.Repo
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    var albums: MutableLiveData<List<Album>?> = MutableLiveData()

    fun getAlbums(){
        viewModelScope.launch {
            try {
                albums.value = repo.getAlbums()
            } catch (e: Exception) {

            }
        }

    }
}