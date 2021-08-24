package rs.sloman.albums.ui.albums

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import rs.sloman.albums.R
import rs.sloman.albums.data.Album

@Composable
fun AlbumsScreen(
    listOfStrings: LiveData<List<Album>>,
    modifier: Modifier = Modifier,
) {
    val myItems = listOfStrings.observeAsState(listOf())

    Scaffold(
        topBar = { AlbumsHeader() },
        content = { AlbumsList(items = myItems.value) }
    )
}

@Composable
fun AlbumsHeader() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
    )
}

@Composable
fun AlbumsList(items: List<Album>) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LazyColumn {
        items(items = items) { scopeItem ->
            AlbumItem(name = scopeItem.title) {
                scope.launch {
                    snackbarHostState.showSnackbar(message = it)
                }
            }
        }
    }
}

@Composable
fun AlbumItem(name: String, onClick: (String) -> Unit) {
    Text(
        text = name,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .clickable {
                onClick(name)
            }
            .padding(24.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}