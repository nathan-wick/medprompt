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
                var freqDropDownOpen by remember { mutableStateOf(false) }
                var selectedFreqIndex by remember { mutableStateOf(0) }

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = freqNumber,
                        onValueChange = { freqNumber = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(modifier = Modifier.weight(3f)) {
                    Text(
                        freqList[selectedFreqIndex],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { freqDropDownOpen = true })
                            .background(Color.Gray))
                    DropdownMenu(
                        expanded = freqDropDownOpen,
                        onDismissRequest = { freqDropDownOpen = false }
                    ) {
                        freqList.forEachIndexed { index, freq ->
                            DropdownMenuItem(onClick = {
                                selectedFreqIndex = index
                                freqDropDownOpen = false
                            }) {
                                Text(text = freq, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }

            Text(text = "Dose Size (Optional)")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                var doseSize by remember { mutableStateOf("") }
                val unitList = listOf("MG", "ML")
                var doseSizeDropDownOpen by remember { mutableStateOf(false) }
                var selectedDoseSizeIndex by remember { mutableStateOf(0) }

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = doseSize,
                        onValueChange = { doseSize = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(modifier = Modifier.weight(3f)) {
                    Text(
                        unitList[selectedDoseSizeIndex],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { doseSizeDropDownOpen = true })
                            .background(Color.Gray))
                    DropdownMenu(
                        expanded = doseSizeDropDownOpen,
                        onDismissRequest = { doseSizeDropDownOpen = false }
                    ) {
                        unitList.forEachIndexed { index, unit ->
                            DropdownMenuItem(onClick = {
                                selectedDoseSizeIndex = index
                                doseSizeDropDownOpen = false
                            }) {
                                Text(text = unit, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }

            Text(text = "Stock Size (Optional)")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                var stockSizeNumber by remember { mutableStateOf("") }
                val stockSizeList = listOf("MG", "ML")
                var stockSizeDropDownOpen by remember { mutableStateOf(false) }
                var selectedStockSizeIndex by remember { mutableStateOf(0) }

                Box(modifier = Modifier.weight(3f)) {
                    TextField(
                        value = stockSizeNumber,
                        onValueChange = { stockSizeNumber = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(modifier = Modifier.weight(3f)) {
                    Text(
                        stockSizeList[selectedStockSizeIndex],
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { stockSizeDropDownOpen = true })
                            .background(Color.Gray))
                    DropdownMenu(
                        expanded = stockSizeDropDownOpen,
                        onDismissRequest = { stockSizeDropDownOpen = false }
                    ) {
                        stockSizeList.forEachIndexed { index, stockSize ->
                            DropdownMenuItem(onClick = {
                                selectedStockSizeIndex = index
                                stockSizeDropDownOpen = false
                            }) {
                                Text(text = stockSize, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
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