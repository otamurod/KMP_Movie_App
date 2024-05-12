package uz.otamurod.kmp.movieapp.android.presentation.home

import uz.otamurod.kmp.movieapp.domain.model.Movie

data class HomeScreenState(
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var isLoadingFinished: Boolean = false
)