package uz.otamurod.kmp.movieapp.android.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home : Destination {
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

object MovieDetail : Destination {
    val arguments = listOf(
        navArgument(name = "movieId") { type = NavType.IntType }
    )
    override val title: String
        get() = "Movie details"
    override val route: String
        get() = "movie_detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"
}

val movieDestinations = listOf(Home, MovieDetail )