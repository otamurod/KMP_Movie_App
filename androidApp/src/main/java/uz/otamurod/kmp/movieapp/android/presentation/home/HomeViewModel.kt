package uz.otamurod.kmp.movieapp.android.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.otamurod.kmp.movieapp.domain.usecase.GetMoviesUseCase

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    init {
        loadMovies(forceReload = false)
    }

    fun loadMovies(forceReload: Boolean = false) {
        if (uiState.isLoading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(isRefreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies

                currentPage += 1
                uiState = uiState.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isLoadingFinished = resultMovies.isEmpty(),
                    movies = movies
                )
                Log.d(TAG, "loadMovies: $movies")
            } catch (error: Throwable) {
                uiState = uiState.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isLoadingFinished = true,
                    errorMessage = "Could not load movies: ${error.localizedMessage}"
                )
            }
        }
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}