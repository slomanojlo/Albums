package rs.sloman.albums.ui.albums

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import rs.sloman.albums.R
import rs.sloman.albums.data.Album
import rs.sloman.albums.data.Status
import rs.sloman.albums.ui.viewmodel.AlbumsViewModel


@Composable
fun AlbumsScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AlbumsViewModel,
    onRetry: (() -> Unit)
) {
    val myItems = viewModel.albums.observeAsState()
    val status = viewModel.status.observeAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AlbumsHeader() },
        content = {
            AlbumsList(
                items = myItems.value,
                scaffoldState = scaffoldState,
            ){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(message = it)
                }
            }

            when (status.value) {
                Status.ERROR -> Retry(onRetry = onRetry)
                Status.LOADING -> LoadingProgressBar()
                Status.SUCCESS -> Log.d("AlbumsCompose", "Data loaded.")
            }
        })
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
fun Retry(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun AlbumsList(
    items: List<Album>?,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onClick: (String) -> Unit
) {

    items?.let {
        LazyColumn {
            items(items = it) { scopeItem ->
                AlbumItem(name = scopeItem.title,
                    onClick = onClick
                )
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