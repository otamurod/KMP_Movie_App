package uz.otamurod.kmp.movieapp.android.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.otamurod.kmp.movieapp.android.Red
import uz.otamurod.kmp.movieapp.android.presentation.home.components.MovieListItem
import uz.otamurod.kmp.movieapp.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
    navigateToDetail: (Movie) -> Unit
) {
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullToRefresh(
                state = pullRefreshState,
                isRefreshing = uiState.isRefreshing,
                enabled = { true },
                onRefresh = { loadNextMovies(true) }),
        isRefreshing = uiState.isRefreshing,
        onRefresh = { loadNextMovies(true) }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                items = uiState.movies,
                key = { _, movie -> movie.id }
            ) { index: Int, movie: Movie ->
                MovieListItem(movie = movie, onMovieClick = { navigateToDetail(movie) })

                // We will check if we need to load more movies
                if (index >= uiState.movies.size - 1 && !uiState.isLoading && !uiState.isLoadingFinished) {
                    LaunchedEffect(key1 = Unit) {
                        loadNextMovies(false)
                    }
                }
            }

            if (uiState.isLoading && uiState.movies.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(color = Red)
                    }
                }
            }
        }
    }
}

@Preview(name = "HomeScreen", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        modifier = Modifier,
        uiState = HomeScreenState(
            isLoading = true,
            isRefreshing = true,
            movies = listOf(
                Movie(
                    id = 1_000,
                    title = "Movie Title Here",
                    description = "Movie Description",
                    imageUrl = "https://www.example.com/blabla",
                    releaseDate = "11-05-2024"
                ),
                Movie(
                    id = 1_001,
                    title = "Movie Title2 Here",
                    description = "Movie Description 2",
                    imageUrl = "https://www.example.com/blabla",
                    releaseDate = "12-05-2024"
                )
            ),
            errorMessage = null,
            isLoadingFinished = false
        ),
        loadNextMovies = { },
        navigateToDetail = {
            Movie(
                id = 1_000,
                title = "Movie Title Here",
                description = "Movie Description",
                imageUrl = "https://www.example.com/blabla",
                releaseDate = "11-05-2024"
            )
        }
    )
}