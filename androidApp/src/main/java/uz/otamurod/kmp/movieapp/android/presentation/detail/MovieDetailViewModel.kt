package uz.otamurod.kmp.movieapp.android.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.otamurod.kmp.movieapp.domain.usecase.GetMovieUseCase

class MovieDetailViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    movieId: Int
) : ViewModel() {
    var uiState by mutableStateOf(MovieDetailScreenState())

    init {
        loadMovie(movieId = movieId)
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            uiState = try {
                val movie = getMovieUseCase(movieId = movieId)
                uiState.copy(isLoading = false, movie = movie)
            } catch (error: Throwable) {
                uiState.copy(
                    isLoading = false,
                    errorMessage = "Could not load movie: ${error.localizedMessage}"
                )
            }
        }
    }
}