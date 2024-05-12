package uz.otamurod.kmp.movieapp.util

import org.koin.core.context.startKoin
import uz.otamurod.kmp.movieapp.di.getSharedModules

/**
 * We should create a function to start Koin since Swift cannot do it
 */
fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}