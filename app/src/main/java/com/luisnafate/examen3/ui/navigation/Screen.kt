package com.luisnafate.examen3.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object RaceList : Screen("race_list")
    object RaceDetail : Screen("race_detail/{season}/{round}") {
        fun createRoute(season: String, round: String) = "race_detail/$season/$round"
    }
    object Search : Screen("search")
    object DriverDetail : Screen("driver_detail/{driverId}") {
        fun createRoute(driverId: String) = "driver_detail/$driverId"
    }
    object Favorites : Screen("favorites")
}
