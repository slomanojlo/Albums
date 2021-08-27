package rs.sloman.albums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.Status
import rs.sloman.albums.ui.repo.Repo
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    val albums: LiveData<List<Album>?> = repo.getLiveDataAlbumsFromDB()

    private val _status: MutableLiveData<Status> = MutableLiveData<Status>(Status.LOADING)
    val status: LiveData<Status> = _status.asLiveData()

    init {
        fetchAlbumsFromServer()
    }

    fun fetchAlbumsFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _status.postValue(Status.LOADING)
                repo.updateAlbums()
                _status.postValue(Status.SUCCESS)
            } catch (e: Exception) {
                val albums = repo.getAlbumsFromDB()
                _status.postValue(if (albums.isNullOrEmpty()) Status.ERROR else Status.SUCCESS)
            }
        }
    }
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>