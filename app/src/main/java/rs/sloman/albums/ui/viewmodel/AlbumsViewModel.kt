package rs.sloman.albums.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.sloman.albums.data.Album
import rs.sloman.albums.ui.repo.Repo
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private val _albums : MutableStateFlow<List<Album>?> = MutableStateFlow(emptyList())
    val albums: StateFlow<List<Album>?> = _albums

    init {
        fetchAlbums()

    }

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albums = repo.getAlbums()
                _albums.value = albums
            } catch (e: Exception) {
                Log.d("Exception", "occurred")
            }
        }
    }
}