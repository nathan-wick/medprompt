package com.medprompt

sealed class Screen(val route: String) {
    object Home: Screen(route = "Home_Screen")
    object Medication: Screen(route = "Medication_Screen")
    object Appointment: Screen(route = "Appointment_Screen")
    object Form: Screen(route = "form_screen")
}
