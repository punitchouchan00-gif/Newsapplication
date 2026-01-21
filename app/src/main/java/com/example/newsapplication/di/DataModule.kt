package com.example.newsapplication.di

import com.example.newsapplication.Data.Service.NewsApiService
import com.example.newsapplication.Data.repositoryimpl.NewsRepositoryImpl
import com.example.newsapplication.domain.Repostaory
import com.example.newsapplication.presentation.NewsViewModel
import com.example.newsapplication.presentation.SplashScreen.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


@Singleton
@Provides
fun ProvideViewModel(repostaory: Repostaory): NewsViewModel {
    return NewsViewModel(repostaory)
}
    @Singleton
    @Provides
    fun ProvideApiService(client: HttpClient): NewsApiService{
        return NewsApiService(client)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                url("https://newsdata.io/api/1/")
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }


@Provides
@Singleton
fun provideNewsRepository(apiService: NewsApiService): Repostaory {
    return NewsRepositoryImpl(apiService)
}
@Provides
@Singleton
fun provideSplasScreenViewModel(repostaory: Repostaory): SplashViewModel {
    return SplashViewModel(repostaory)
}


}
