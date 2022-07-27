package com.medprompt.screens.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.medprompt.AppState
import com.medprompt.ui.theme.defaultPadding

@Composable
fun EditAppointmentScreen(appState: AppState, documentId: String?) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Edit Apps here")
        Text(text = "$documentId")
    }
}