package com.medprompt

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object EmailPasswordForm: Screen(route = "email_password_screen")
    object Home: Screen(route = "home_screen")
    object Medication: Screen(route = "medication_screen")
    object Appointment: Screen(route = "appointment_screen")
    object Form: Screen(route = "form_screen")
}
