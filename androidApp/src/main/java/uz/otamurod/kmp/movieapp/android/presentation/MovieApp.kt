package uz.otamurod.kmp.movieapp.android.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uz.otamurod.kmp.movieapp.android.presentation.common.MovieAppBar
import uz.otamurod.kmp.movieapp.android.presentation.navigation.Home
import uz.otamurod.kmp.movieapp.android.presentation.navigation.SetUpNavGraph
import uz.otamurod.kmp.movieapp.android.presentation.navigation.movieDestinations

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.surface

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPadding ->
        SetUpNavGraph(navController = navController, innerPadding = innerPadding)
    }
}

@Preview(name = "MovieApp")
@Composable
private fun PreviewMovieApp() {
    MovieApp()
}