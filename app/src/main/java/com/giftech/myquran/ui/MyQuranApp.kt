package com.giftech.myquran.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.giftech.myquran.ui.navigation.Screen
import com.giftech.myquran.ui.screen.home.HomeScreen
import com.giftech.myquran.ui.screen.search.SearchScreen
import com.giftech.myquran.ui.screen.surah.SurahScreen
import com.giftech.myquran.ui.screen.welcome.WelcomeScreen

@ExperimentalComposeUiApi
@Composable
fun MyQuranApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartedClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onSurahClick = {
                    navController.navigate(Screen.Surah.createRoute(it))
                },
                onLastReadClick = {
                    navController.navigate(Screen.Surah.createRoute(it))
                },
                onSearchClick = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
        composable(
            route = Screen.Surah.route,
            arguments = listOf(navArgument("nomorSurah") { type = NavType.IntType }),
        ) {
            val nomorSurah = it.arguments?.getInt("nomorSurah") ?: 1
            SurahScreen(
                nomorSurah = nomorSurah,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Search.route){
            SearchScreen(
                onSurahClicked = {
                    navController.navigate(Screen.Surah.createRoute(it))
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}