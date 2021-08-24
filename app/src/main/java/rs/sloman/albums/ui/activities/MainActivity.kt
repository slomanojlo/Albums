package rs.sloman.albums.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.albums.ui.theme.AlbumsTheme
import rs.sloman.albums.ui.viewmodel.AlbumsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAlbums()

        viewModel.albums.observe(this){
            Log.d("Test", "Albums")
        }

        setContent {
            AlbumsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlbumsTheme {
        Greeting("Android")
    }
}