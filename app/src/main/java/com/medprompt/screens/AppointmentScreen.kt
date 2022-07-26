package com.medprompt.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.medprompt.AppState
import com.medprompt.Screen
import com.medprompt.components.DateTimePicker
import com.medprompt.components.DropDown
import com.medprompt.components.HeaderOptions
import com.medprompt.components.InputField
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import kotlinx.coroutines.CoroutineScope
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter

data class Appointment (
    val datetime: String,
    val freqAmount: Number,
    val freqType: String,
    val appName: String
)

@Composable
fun AppointmentScreen(appState: AppState) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    var appName by remember { mutableStateOf("") }
    var freqAmount by remember { mutableStateOf(1) }

    var isOpen by remember { mutableStateOf(false) }
    val freqList = listOf("Week", "Month")
    var freqTypeSelectedIndex by remember { mutableStateOf(0) }
    var freqType by remember { mutableStateOf(freqList[freqTypeSelectedIndex]) }

    MedpromptTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderOptions(
                navController = appState.navController,
                contextLabel = "Appointment",
                addButtonOnClick = {
                    if (user != null && appName.isNotEmpty()) {
                        val addApp = firestore
                            .collection("appointments")
                            .document(user.uid)
                            .collection("appointments")
                            .document()
                            .set(
                                Appointment(
                                    datetime = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                                    freqAmount = freqAmount,
                                    freqType = freqType,
                                    appName = appName
                                )
                            )
                        addApp.addOnSuccessListener(OnSuccessListener {
                            appState.navController.navigate(Screen.Home.route)
                        })
                    }
                }
            )

            Text(text = "Appointment Name")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(55.dp)) {
                
                InputField(weight = 1f, value = appName, onValueChange = { appName = it })
            }

            DateTimePicker(label = "Date and Time of Appointment")

            Text(text = "Frequency of the Appointment")
            Row(modifier = Modifier
                .padding(5.dp)
                .height(55.dp)) {

                InputField(
                    weight = 3f,
                    placeholder = "Times every...",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = freqAmount.toString(),
                    onValueChange = {
                        if (it.isBlank()) {
                            freqAmount = 1;
                        }
                        else if (freqAmount > 0 && freqAmount <= 20) {
                            freqAmount = it.toInt()
                        } else {
                            Toast.makeText(context, "Frequency of App. must be below or equal 20", Toast.LENGTH_LONG).show()
                        }

                    }
                )

                Box(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(weight = 3f)
                        .border(width = 4.dp, color = Blue200, shape = CircleShape)
                        .clickable { isOpen = !isOpen },
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Text(
                            text = freqList[freqTypeSelectedIndex],
                            textAlign = TextAlign.Center
                        )

                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                        DropdownMenu(
                            expanded = isOpen,
                            onDismissRequest = { isOpen = false }
                        ) {
                            freqList.forEachIndexed { index, itemText ->
                                DropdownMenuItem(onClick = {
                                    freqTypeSelectedIndex = index
                                    freqType = freqList[freqTypeSelectedIndex]
                                    isOpen = false
                                }) {
                                    Text(text = itemText, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentScreen_Preview() {
    MedpromptTheme {
        AppointmentScreen(appState = AppState(rememberScaffoldState(), rememberNavController(), rememberCoroutineScope()))
    }
}