package com.medprompt.screens

import android.renderscript.ScriptGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.defaultPadding
import java.time.Instant
import java.time.format.DateTimeFormatter

data class Medication(
    val datetime: String,
    val freqAmount: Number,
    val freqType: String,
    val doseSize: Number,
    val dozeSizeUnit: String,
    val stockSize: Number,
    val stockSizeUnit: String,
    val medName: String
)

@Composable
fun MedicationScreen(appState: AppState) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    var medName by remember { mutableStateOf("") }
    var freqAmount by remember { mutableStateOf(1) }
    var doseSize by remember { mutableStateOf(0) }
    var stockSize by remember { mutableStateOf(0) }

    Column (modifier = Modifier.fillMaxSize()) {
        HeaderOptions(
            navController = appState.navController,
            contextLabel = "Medication",
            addButtonOnClick = {
                if (user != null && medName.isNotEmpty()) {
                    val addMed = firestore
                        .collection("appointments")
                        .document(user.uid)
                        .collection("medications")
                        .document()
                        .set(
                            Medication(
                                datetime = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                                freqAmount = freqAmount,
                                freqType = "Week",
                                doseSize = doseSize,
                                dozeSizeUnit = "MG",
                                stockSize = stockSize,
                                stockSizeUnit = "MG",
                                medName = medName
                            )
                        )
                    addMed.addOnSuccessListener(OnSuccessListener {
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

        DateTimePicker(label = "Date and Time to take Medication")

        Text(text = "Frequency to take Medication")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {
            val freqList = listOf("Weeks", "Months", "Years")

            InputField(
                placeholder = "Times every...",
                weight = 3f,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = freqAmount.toString(),
                onValueChange = {
                    if (it.isBlank() || (it.toInt() >= 20)) {
                        freqAmount = 1;
                        Toast.makeText(context, "Must be at least 1 but below 21", Toast.LENGTH_LONG).show()
                    } else {
                        freqAmount = it.toInt()
                    }
                }
            )
            DropDown(weight = 3f, items = freqList)
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
                    if (it.isBlank()) {
                        doseSize = 0;
                    } else {
                        doseSize = it.toInt()
                    }
                }
            )
            DropDown(weight = 3f, items = unitList)
        }

        Text(text = "Stock Size (Optional)")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(50.dp)) {
            val stockSizeList = listOf("MG", "ML")

            InputField(
                weight = 3f,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = stockSize.toString(),
                onValueChange = {
                    if (it.isBlank()) {
                        stockSize = 0;
                    } else {
                        stockSize = it.toInt()
                    }
                }
            )
            DropDown(weight = 3f, items = stockSizeList)
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