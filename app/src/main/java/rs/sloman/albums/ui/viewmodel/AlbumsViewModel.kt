package rs.sloman.albums.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.Status
import rs.sloman.albums.ui.repo.Repo
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    val albums: LiveData<List<Album>?> = repo.getAlbumsFromDB().asLiveData()

    private val _status : MutableLiveData<Status> = MutableLiveData<Status>(Status.LOADING)
    val status: LiveData<Status> = _status.asLiveData()

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                _status.value = Status.LOADING
                val albums = repo.getAlbums()
                repo.updateAlbums(albums = albums)
                _status.value = Status.SUCCESS
            } catch (e: Exception) {
                Log.d("Exception", "occurred")
                _status.value = if(albums.value != null) Status.SUCCESS else Status.ERROR
            }
        }
    }
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>