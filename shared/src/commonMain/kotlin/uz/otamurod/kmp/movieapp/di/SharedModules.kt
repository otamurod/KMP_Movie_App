package uz.otamurod.kmp.movieapp.di

import org.koin.dsl.module
import uz.otamurod.kmp.movieapp.data.remote.api.MovieKtorApiService
import uz.otamurod.kmp.movieapp.data.remote.datasource.MovieRemoteDataSource
import uz.otamurod.kmp.movieapp.data.repository.MovieRepository
import uz.otamurod.kmp.movieapp.domain.repository.MovieRepositoryApi
import uz.otamurod.kmp.movieapp.domain.usecase.GetMovieUseCase
import uz.otamurod.kmp.movieapp.domain.usecase.GetMoviesUseCase
import uz.otamurod.kmp.movieapp.util.provideDispatcher

private val dataModule = module {
    factory { MovieRemoteDataSource(get(), get()) }
    factory { MovieKtorApiService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepositoryApi> { MovieRepository(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules