package com.medprompt

/**
 * Simply holds a constant value for the routes for each of our screens
 */
sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Medication: Screen(route = "medication_screen")
    object EditMedication: Screen(route = "edit_meds_screen")
    object Appointment: Screen(route = "appointment_screen")
    object EditAppointment: Screen(route = "edit_apps_screen")
    object Form: Screen(route = "form_screen")
}
