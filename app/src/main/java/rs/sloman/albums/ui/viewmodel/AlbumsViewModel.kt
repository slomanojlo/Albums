package rs.sloman.albums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.Status
import rs.sloman.albums.ui.repo.Repo
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    val albums: LiveData<List<Album>?> = repo.getAlbumsFromDB()

    private val _status: MutableLiveData<Status> = MutableLiveData<Status>(Status.LOADING)
    val status: LiveData<Status> = _status.asLiveData()

    init {
        fetchAlbumsFromServer()
    }

    fun fetchAlbumsFromServer() {
        viewModelScope.launch {
            try {
                _status.value = Status.LOADING
                repo.updateAlbums()
                _status.value = Status.SUCCESS
            } catch (e: Exception) {
                val albums = repo.getAlbumsFromDB()
                _status.value = if (albums.value != null) Status.SUCCESS else Status.ERROR
            }
        }
    }
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>