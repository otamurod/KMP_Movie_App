package uz.otamurod.kmp.movieapp.data.remote.entities

import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResultResponse(
    val results: List<MovieResponse>
)
