package uz.otamurod.kmp.movieapp.android.presentation.detail

import uz.otamurod.kmp.movieapp.domain.model.Movie

data class MovieDetailScreenState(
    var isLoading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null
)
