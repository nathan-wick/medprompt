package com.medprompt.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.*
import com.medprompt.components.Button
import com.medprompt.components.DrawerHeader
import com.medprompt.ui.theme.*
import kotlinx.coroutines.launch

data class HomeFeedItem(
    val docId: String,
    val title: String,
    val datetime: String
)

@Composable
fun HomeScreen(appState: AppState) {
    val context = LocalContext.current
    Scaffold(
        content = {
            AppointmentList(appState, context)
        },
        floatingActionButton = { AddButton(appState.navController) },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun AppointmentList(appState: AppState, context: Context) {
    val scaffoldState = appState.scaffoldState
    val scope = appState.scope

    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    val homeFeed by remember { mutableStateOf(ArrayList<HomeFeedItem>()) }

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            com.medprompt.components.AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            if (user != null) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            AuthUI
                                .getInstance()
                                .signOut(context)
                                .addOnCompleteListener(OnCompleteListener {
                                    if (it.isComplete) {
                                        appState.navController.navigate(Screen.Home.route)
                                    }
                                })

                            appState.navController.navigate(Screen.Home.route)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Sign Out Icon",
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Sign Out",
                        modifier = Modifier
                            .height(100.dp)
                            .wrapContentHeight()
                    )
                }
            }
            else {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    LoginActivity::class.java
                                )
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account Login Icon",
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Login / Signup",
                        modifier = Modifier
                            .height(100.dp)
                            .wrapContentHeight()
                    )
                }
            }
        },
        content = {
            if (user != null) {
                    firestore
                    .collection("appointments")
                    .document(user.uid)
                    .collection("home-feed")
                        .addSnapshotListener { docSnapshot, e ->
                            docSnapshot?.forEach {

                                val homeFeedItem = HomeFeedItem(
                                    docId = it.id,
                                    title = it.get("title").toString(),
                                    datetime = it.get("datetime").toString()
                                )

                                if (!homeFeed.contains(homeFeedItem)) {
                                    homeFeed.add(homeFeedItem)
                                }
                                Log.d("TEST", it.get("title").toString())
                            }
                        }
            } else {
                Text(text = "Log in to add")
            }

            Column {
                homeFeed.forEachIndexed  { index, item ->
                    AppItem(appText = homeFeed.get(index).title, appDate = homeFeed.get(index).datetime)
                }
            }
        }
    )
}

@Composable
fun AppItem(appText : String, appDate: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 1.dp),
        color = Silver100
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = appText, color = Color.Black, modifier = Modifier.padding(horizontal = 10.dp))
            Text(text = appDate, color = Blue200, modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}

@Composable
fun AddButton (navController: NavController) {
//    TODO: Make the Add Icon Button a blue circle with options
    var openAddOptions by remember { mutableStateOf(false) }

    Column (
        horizontalAlignment = Alignment.End
    ){

        if (openAddOptions) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.width(170.dp)
            ) {
                Row(modifier = Modifier.padding(defaultPadding)) {
                    Button(text = "Medication", onClick = {
                        navController.navigate(route = Screen.Medication.route)
                })
                }
                Row(modifier = Modifier.padding(defaultPadding)) {
                    Button(text = "Appointment", onClick = {
                        navController.navigate(route = Screen.Appointment.route)
                    })
                }
                Row(modifier = Modifier.padding(defaultPadding), ) {
                    Button(text = "Form", onClick = {
                        navController.navigate(route = Screen.Form.route)
                    })
                }
            }
        }

        IconButton(onClick = {
            openAddOptions = !openAddOptions
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Appointments",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MedpromptTheme {
        HomeScreen(appState = AppState(rememberScaffoldState(), rememberNavController(), rememberCoroutineScope()))
    }
}
