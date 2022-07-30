package com.medprompt.screens.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.AppState
import com.medprompt.Screen
import com.medprompt.components.HeaderOptions

@Composable
fun EditMedicationScreen(appState: AppState, documentId: String?) {
    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()

    var medName by remember { mutableStateOf("") }

    if (user != null && documentId != null) {
        val docRef = firestore
            .collection("appointments")
            .document(user.uid)
            .collection("medications")
            .document(documentId)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    medName = document.get("medName").toString()
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
                            .collection("medications")
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
            Text(text = medName)
        }
    }
}