package uz.otamurod.kmp.movieapp.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import uz.otamurod.kmp.movieapp.data.util.Constants.API_KEY
import uz.otamurod.kmp.movieapp.data.util.Constants.BASE_URL

internal abstract class MovieKtorApiClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path:String){
        url {
            takeFrom(BASE_URL)
            path("3", path)
            parameter("api_key", API_KEY)
        }
    }
}