package uz.otamurod.kmp.movieapp.android.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import uz.otamurod.kmp.movieapp.android.presentation.detail.MovieDetailScreen
import uz.otamurod.kmp.movieapp.android.presentation.detail.MovieDetailViewModel
import uz.otamurod.kmp.movieapp.android.presentation.home.HomeScreen
import uz.otamurod.kmp.movieapp.android.presentation.home.HomeViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(innerPadding),
        startDestination = Home.routeWithArgs
    ) {
        composable(Home.routeWithArgs) {
            val homeViewModel: HomeViewModel = koinViewModel()
            HomeScreen(
                uiState = homeViewModel.uiState,
                loadNextMovies = { homeViewModel.loadMovies(forceReload = it) },
                navigateToDetail = {
                    navController.navigate("${MovieDetail.route}/${it.id}")
                }
            )
        }
        composable(MovieDetail.routeWithArgs) {
            val movieId = it.arguments?.getString("movieId") ?: "0"
            Log.d("MovieApp", "MovieId: $movieId")
            val movieDetailViewModel: MovieDetailViewModel = koinViewModel(
                parameters = { parametersOf(movieId.toInt()) }
            )

            MovieDetailScreen(uiState = movieDetailViewModel.uiState)
        }
    }
}

@Preview(name = "SetUpNavGraph")
@Composable
private fun PreviewSetUpNavGraph() {
    SetUpNavGraph(navController = rememberNavController(), innerPadding = PaddingValues(12.dp))
}