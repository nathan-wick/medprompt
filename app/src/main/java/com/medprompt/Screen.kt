package com.medprompt

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Medication: Screen(route = "medication_screen")
    object Appointment: Screen(route = "appointment_screen")
    object Form: Screen(route = "form_screen")
}