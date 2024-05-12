package uz.otamurod.kmp.movieapp.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.otamurod.kmp.movieapp.android.presentation.di.appModule
import uz.otamurod.kmp.movieapp.di.getSharedModules

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(appModule + getSharedModules())
        }
    }
}