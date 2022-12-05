package com.giftech.myquran.ui.navigation


sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Surah : Screen("surah/{surahId}") {
        fun createRoute(surahId: Int) = "surah/$surahId"
    }
    object Search : Screen("search")
}