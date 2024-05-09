package uz.otamurod.kmp.movieapp.domain.repository

import uz.otamurod.kmp.movieapp.domain.model.Movie

internal interface MovieRepositoryApi {
    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovie(movieId: Int): Movie
}