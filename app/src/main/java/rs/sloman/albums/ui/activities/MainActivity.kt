package rs.sloman.albums.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.albums.ui.albums.AlbumsScreen
import rs.sloman.albums.ui.theme.AlbumsTheme
import rs.sloman.albums.ui.viewmodel.AlbumsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlbumsTheme {
                AlbumsScreen(
                    modifier = Modifier,
                    viewModel = viewModel,
                ) {
                    viewModel.fetchAlbumsFromServer()
                }
            }
        }

    }
}



