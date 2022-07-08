package com.medprompt.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.Screen
import com.medprompt.components.DateTimePicker
import com.medprompt.components.DropDown
import com.medprompt.components.HeaderOptions
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun MedicationScreen(navController: NavController) {
        Column (modifier = Modifier.fillMaxSize()) {
            HeaderOptions(navController = navController, contextLabel = "Medication")

            Text(text = "Medication Name")
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

            Row {
                DateTimePicker(label = "Date and Time to take Medication")
            }

            Text(text = "Frequency to take Medication")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                var freqNumber by remember { mutableStateOf("") }
                val freqList = listOf("Weeks", "Months", "Years")

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = freqNumber,
                        onValueChange = { freqNumber = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                DropDown(weight = 3f, items = freqList)
            }

            Text(text = "Dose Size (Optional)")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                var doseSize by remember { mutableStateOf("") }
                val unitList = listOf("MG", "ML")

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = doseSize,
                        onValueChange = { doseSize = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                DropDown(weight = 3f, items = unitList)
            }

            Text(text = "Stock Size (Optional)")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                var stockSizeNumber by remember { mutableStateOf("") }
                val stockSizeList = listOf("MG", "ML")

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = stockSizeNumber,
                        onValueChange = { stockSizeNumber = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                DropDown(weight = 3f, items = stockSizeList)
            }

        }
}


@Preview(showBackground = true)
@Composable
fun MedicationScreen_Preview() {
    MedpromptTheme {
        MedicationScreen(navController = rememberNavController())
    }
}