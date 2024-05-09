package uz.otamurod.kmp.movieapp.data.repository

import uz.otamurod.kmp.movieapp.data.mappers.asBO
import uz.otamurod.kmp.movieapp.data.remote.datasource.MovieRemoteDataSource
import uz.otamurod.kmp.movieapp.domain.model.Movie
import uz.otamurod.kmp.movieapp.domain.repository.MovieRepositoryApi

internal class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepositoryApi {
    override suspend fun getMovies(page: Int): List<Movie> {
        return movieRemoteDataSource.getMovies(page = page).results.map {
            it.asBO()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return movieRemoteDataSource.getMovie(movieId = movieId).asBO()
    }
}