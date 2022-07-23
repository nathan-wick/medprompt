package com.medprompt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medprompt.screens.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController =  navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            content = { HomeScreen(navController = navController) }
        )
        composable(
            route = Screen.Medication.route,
            content = { MedicationScreen(navController = navController) }
        )
        composable(
            route = Screen.Appointment.route,
            content = { AppointmentScreen(navController = navController) }
        )
        composable(
            route = Screen.Form.route,
            content = { FormScreen(navController = navController) }
        )
    }
}