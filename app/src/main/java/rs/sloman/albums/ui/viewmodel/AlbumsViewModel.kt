package rs.sloman.albums.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.sloman.albums.ui.repo.Repository
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
}