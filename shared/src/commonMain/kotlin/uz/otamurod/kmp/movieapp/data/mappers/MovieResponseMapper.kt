package uz.otamurod.kmp.movieapp.data.mappers

import uz.otamurod.kmp.movieapp.data.remote.entities.MovieResponse
import uz.otamurod.kmp.movieapp.domain.model.Movie

internal fun MovieResponse.asBO(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = getImageUrl(posterImage),
        releaseDate = releaseDate
    )
}

private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"