package uz.otamurod.kmp.movieapp.android.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.otamurod.kmp.movieapp.android.presentation.detail.MovieDetailViewModel
import uz.otamurod.kmp.movieapp.android.presentation.home.HomeViewModel

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { parameters ->
        MovieDetailViewModel(
            getMovieUseCase = get(),
            movieId = parameters.get()
        )
    }
}