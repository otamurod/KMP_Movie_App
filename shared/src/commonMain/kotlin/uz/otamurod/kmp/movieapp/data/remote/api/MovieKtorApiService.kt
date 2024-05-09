package uz.otamurod.kmp.movieapp.data.remote.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import uz.otamurod.kmp.movieapp.data.remote.entities.MovieResponse
import uz.otamurod.kmp.movieapp.data.remote.entities.MoviesResultResponse

internal class MovieKtorApiService : MovieKtorApiClient() {
    suspend fun getMovies(page: Int = 1): MoviesResultResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieResponse = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}