package rs.sloman.albums.ui.viewmodel

import androidx.lifecycle.LiveData
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

    private var _albums: MutableLiveData<List<Album>> = MutableLiveData()
    var albums = _albums.asLiveData()

    init {
        viewModelScope.launch {
            _albums.value = repo.getAlbums()
        }

    }
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>