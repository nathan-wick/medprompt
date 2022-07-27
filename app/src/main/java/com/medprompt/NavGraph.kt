package com.medprompt

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.medprompt.screens.*
import com.medprompt.screens.edit.EditAppointmentScreen
import com.medprompt.screens.edit.EditMedicationScreen

@Composable
fun SetupNavGraph(appState: AppState) {
    NavHost(
        navController =  appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            content = { HomeScreen(appState = appState) }
        )
        composable(
            route = Screen.Medication.route,
            content = { MedicationScreen(appState = appState) }
        )
        composable(
            route = Screen.EditMedication.route + "/{documentId}",
            arguments = listOf(navArgument("documentId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            EditMedicationScreen(appState = appState, documentId = navBackStackEntry.arguments?.getString("documentId"))
        }
        composable(
            route = Screen.Appointment.route,
            content = { AppointmentScreen(appState = appState) }
        )
        composable(
            route = Screen.EditAppointment.route + "/{documentId}",
            arguments = listOf(navArgument("documentId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            EditAppointmentScreen(appState = appState, documentId = navBackStackEntry.arguments?.getString("documentId"))
        }
        composable(
            route = Screen.Form.route,
            content = { FormScreen(navController = appState.navController) }
        )
    }
}