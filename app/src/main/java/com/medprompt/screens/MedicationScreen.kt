package com.medprompt.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.AppState
import com.medprompt.Screen
import com.medprompt.components.DateTimePicker
import com.medprompt.components.DropDown
import com.medprompt.components.HeaderOptions
import com.medprompt.components.InputField
import com.medprompt.dto.HomeFeedItem
import com.medprompt.dto.Medication
import com.medprompt.dto.ScreenType
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.defaultPadding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun MedicationScreen(appState: AppState) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()

    val stockSizeList = listOf("MG", "ML")
    val freqList = listOf("Week", "Month", "Year")

    var medName by remember { mutableStateOf("") }
    var freqAmount by remember { mutableStateOf(1) }
    var doseSize by remember { mutableStateOf(0) }
    var stockSize by remember { mutableStateOf(0) }

    var selectedFreqType by remember { mutableStateOf(freqList[0]) }
    var selectedDoseSize by remember { mutableStateOf(stockSizeList[0]) }
    var selectedStockSize by remember { mutableStateOf(stockSizeList[0]) }

    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val formatted = current.format(formatter)

    var dateTime by remember { mutableStateOf(formatted) }

    Column (modifier = Modifier.fillMaxSize()) {
        HeaderOptions(
            navController = appState.navController,
            contextLabel = "Add Medication",
            addButtonOnClick = {
                if (user != null && medName.isNotEmpty()) {
                    val addMed = firestore
                        .collection("appointments")
                        .document(user.uid)
                        .collection("medications")
                        .add(
                            Medication(
                                datetime = dateTime,
                                freqAmount = freqAmount,
                                freqType = selectedFreqType,
                                doseSize = doseSize,
                                dozeSizeUnit = selectedDoseSize,
                                stockSize = stockSize,
                                stockSizeUnit = selectedStockSize,
                                medName = medName
                            )
                        )

                    addMed.addOnSuccessListener(OnSuccessListener {

                        firestore
                            .collection("appointments")
                            .document(user.uid)
                            .collection("home-feed")
                            .document(it.id)
                            .set(
                                HomeFeedItem(
                                    documentId = it.id,
                                    screenType = ScreenType.MEDICATION,
                                    title = medName,
                                    datetime = dateTime
                                )
                            )

                        appState.navController.navigate(Screen.Home.route)
                    })
                }
            })

        Text(text = "Medication Name")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {

            InputField(
                weight = 1f,
                value = medName,
                onValueChange = { medName = it }
            )
        }

        DateTimePicker(label = "Date and Time to take Medication", onSelectedValue = { dateTime = it.toString() })

        Text(text = "Frequency to take Medication")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {

            InputField(
                placeholder = "Times every...",
                weight = 3f,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = freqAmount.toString(),
                onValueChange = {
                    if (it.isNullOrBlank() || it.isNullOrEmpty() || it.toInt() > 100) {
                        freqAmount = 1
                        Toast.makeText(context, "Must be between 0 and 100", Toast.LENGTH_LONG).show()
                    } else {
                        freqAmount = it.toInt()
                    }
                }
            )
            DropDown(
                weight = 3f,
                items = freqList,
                selectedValue = selectedFreqType,
                onSelectedValue = { selectedFreqType = it }
            )
        }

        Text(text = "Dose Size (Optional)")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {
            val unitList = listOf("MG", "ML")

            InputField(
                weight = 3f,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = doseSize.toString(),
                onValueChange = {
                    if (it.isNullOrBlank() || it.isNullOrEmpty()) {
                        doseSize = 0
                    } else {
                        doseSize = it.toInt()
                    }
                }
            )
            DropDown(
                weight = 3f,
                items = stockSizeList,
                selectedValue = selectedDoseSize,
                onSelectedValue = { selectedDoseSize = it }
            )
        }

        Text(text = "Stock Size (Optional)")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {

            InputField(
                weight = 3f,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = stockSize.toString(),
                onValueChange = {
                    if (it.isNullOrBlank() || it.isNullOrEmpty()) {
                        stockSize = 0
                    } else {
                        stockSize = it.toInt()
                    }
                }
            )
            DropDown(
                weight = 3f,
                items = stockSizeList,
                selectedValue = selectedStockSize,
                onSelectedValue = { selectedStockSize = it }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MedicationScreen_Preview() {
    MedpromptTheme {
        MedicationScreen(appState = AppState(rememberScaffoldState(), rememberNavController(), rememberCoroutineScope()))
    }
}