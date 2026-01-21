package com.example.newsapplication.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.presentation.HomeScreen.HomeScreen
import com.example.newsapplication.presentation.SplashScreen.SplashScreen


@Composable
    fun Navigation() {
        val navController = rememberNavController()

        NavHost(navController=navController,
            startDestination = Routes.SplashScreen) {

            composable<Routes.SplashScreen> {
                SplashScreen(navController)
            }
            composable<Routes.HomeScreen> {
                HomeScreen(navController)
            }

        }
}