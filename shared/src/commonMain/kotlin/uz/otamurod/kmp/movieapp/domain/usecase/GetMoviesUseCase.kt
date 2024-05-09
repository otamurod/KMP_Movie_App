package uz.otamurod.kmp.movieapp.domain.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.otamurod.kmp.movieapp.domain.model.Movie
import uz.otamurod.kmp.movieapp.domain.repository.MovieRepositoryApi

class GetMoviesUseCase : KoinComponent {
    // We extend KoinComponent since in iOS side, there is no @inject
    // And it should be not internal so that we can use it on both iOS & Android side

    private val movieRepositoryApi: MovieRepositoryApi by inject()

    // We will override invoke operator
    @Throws(Exception::class) //  The annotation will tell the iOS side that the function can throw an exception
    suspend operator fun invoke(page: Int): List<Movie> {
        return movieRepositoryApi.getMovies(page = page)
    }
}