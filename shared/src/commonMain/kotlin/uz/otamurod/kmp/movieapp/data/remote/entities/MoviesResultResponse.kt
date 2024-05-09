package uz.otamurod.kmp.movieapp.data.remote.entities

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResultResponse(
    val results: List<MovieResponse>
)
