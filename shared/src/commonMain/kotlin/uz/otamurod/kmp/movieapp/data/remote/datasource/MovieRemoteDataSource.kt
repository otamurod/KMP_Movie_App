package uz.otamurod.kmp.movieapp.data.remote.datasource

import kotlinx.coroutines.withContext
import uz.otamurod.kmp.movieapp.data.remote.api.MovieKtorApiService
import uz.otamurod.kmp.movieapp.data.remote.entities.MoviesResultResponse
import uz.otamurod.kmp.movieapp.util.Dispatcher

internal class MovieRemoteDataSource(
    private val movieKtorApiService: MovieKtorApiService,
    private val dispatcher: Dispatcher
) {
    suspend fun getMovies(page: Int = 1) = withContext(dispatcher.io) {
        movieKtorApiService.getMovies(page = page)
    }

    suspend fun getMovie(movieId: Int) = withContext(dispatcher.io) {
        movieKtorApiService.getMovie(movieId = movieId)
    }
}