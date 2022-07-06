package com.medprompt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medprompt.screens.AppointmentScreen
import com.medprompt.screens.FormScreen
import com.medprompt.screens.HomeScreen
import com.medprompt.screens.MedicationScreen

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
            content = { MedicationScreen() }
        )
        composable(
            route = Screen.Appointment.route,
            content = { AppointmentScreen() }
        )
        composable(
            route = Screen.Form.route,
            content = { FormScreen() }
        )
    }
}