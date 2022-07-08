package com.medprompt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.components.DateTimePicker
import com.medprompt.components.DropDown
import com.medprompt.components.HeaderOptions
import com.medprompt.components.InputField
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun AppointmentScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderOptions(navController = navController, contextLabel = "Appointment")

        Text(text = "Appointment Name")
        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {
            var medName by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = medName,
                onValueChange = { medName = it }
            )
        }

        Row() {
            DateTimePicker(label = "Date and Time of Appointment")
        }

        Text(text = "Frequency of the Appointment")
        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {
            val freqList = listOf("Weeks", "Months", "Years")

            InputField(weight = 3f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DropDown(weight = 3f, items = freqList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentScreen_Preview() {
    MedpromptTheme {
        AppointmentScreen(navController = rememberNavController())
    }
}