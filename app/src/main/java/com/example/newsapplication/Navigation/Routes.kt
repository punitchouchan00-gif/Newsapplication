package com.example.newsapplication.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {


    @Serializable
    data object SplashScreen: Routes()

    @Serializable
    data object HomeScreen: Routes()

    @Serializable
    data object DetailsScreen: Routes()

}