package com.medprompt.screens

import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Button
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.medprompt.AppState
import com.medprompt.Screen
import com.medprompt.components.*
import com.medprompt.dto.Appointment
import com.medprompt.dto.CustomDateTime
import com.medprompt.dto.HomeFeedItem
import com.medprompt.dto.ScreenType
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.scope.emptyState
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun AppointmentScreen(appState: AppState) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    var appName by remember { mutableStateOf("") }
    var freqAmount by remember { mutableStateOf(0) }

    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val formatted = current.format(formatter)

    var dateTime by remember { mutableStateOf(formatted) }

    val freqList = listOf("Week", "Month", "Year")
    var selectedFreqType by remember { mutableStateOf(freqList[0]) }
    var document: String = ""

    MedpromptTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderOptions(
                navController = appState.navController,
                contextLabel = "Appointment",
                addButtonOnClick = {
                    if (user != null && appName.isNotEmpty()) {
                        Log.d("DATETIME: ", dateTime)
                        val addApp = firestore
                            .collection("appointments")
                            .document(user.uid)
                            .collection("appointments")
                            .add(
                                Appointment(
                                datetime = dateTime,
                                freqAmount = freqAmount,
                                freqType = selectedFreqType,
                                appName = appName
                            ))

                        addApp.addOnSuccessListener(OnSuccessListener {
                            firestore
                                .collection("appointments")
                                .document(user.uid)
                                .collection("home-feed")
                                .document(it.id)
                                .set(
                                    HomeFeedItem(
                                        documentId = it.id,
                                        screenType = ScreenType.APPOINTMENT,
                                        title = appName,
                                        datetime = dateTime
                                    )
                                )
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

            DateTimePicker(label = "Date and Time of Appointment", onSelectedValue = { dateTime = it.toString() })

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
                        if (it.isNullOrBlank() || it.isNullOrEmpty() || it.toInt() > 100) {
                            freqAmount = 1
                            Toast.makeText(context, "Must be between 0 and 100", Toast.LENGTH_LONG).show()
                        } else {
                            freqAmount = it.toInt()
                        }
                    }
                )
                DropDown(weight = 3f, items = freqList, onSelectedValue = { selectedFreqType = it })
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