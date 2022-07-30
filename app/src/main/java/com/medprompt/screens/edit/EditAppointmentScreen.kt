package com.medprompt.screens.edit

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.AppState
import com.medprompt.Screen
import com.medprompt.components.HeaderOptions

@Composable
fun EditAppointmentScreen(appState: AppState, documentId: String?) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()

    var appName by remember { mutableStateOf("") }
    val freqAmount by remember { mutableStateOf(0) }
    var dateTime by remember { mutableStateOf("") }
    val freqList = listOf("Week", "Month", "Year")
    var selectedFreqType by remember { mutableStateOf(freqList[0]) }

    if (user != null && documentId != null) {
        val docRef = firestore.collection("appointments").document(user.uid).collection("appointments").document(documentId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    appName = document.get("appName").toString()
                    // this is throwing errors: java.lang.NumberFormatException: For input string: "null"
//                    freqAmount = document.get("freqAmount").toString().toInt()
                    dateTime = document.get("datetime").toString()
                    selectedFreqType = document.get("freqType").toString()
                    Toast.makeText(context, appName, Toast.LENGTH_LONG).show()
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row() {
            HeaderOptions(
                navController = appState.navController,
                contextLabel = "Update",
                showDeleteButton = true,
                deleteButtonOnCick = {
                    if (user != null && documentId != null) {
                        val deleteMain = firestore
                            .collection("appointments")
                            .document(user.uid)
                            .collection("appointments")
                            .document(documentId)
                            .delete()

                        deleteMain.addOnSuccessListener {

                            val deleteHomeFeedItem = firestore
                                .collection("appointments")
                                .document(user.uid)
                                .collection("home-feed")
                                .document(documentId)
                                .delete()

                            deleteHomeFeedItem.addOnSuccessListener {
                                appState.navController.navigate(Screen.Home.route)
                            }
                        }
                    }
                }
            )
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = appName)
            Text(text = freqAmount.toString())
            Text(text = dateTime)
            Text(text = selectedFreqType)
        }
    }
}