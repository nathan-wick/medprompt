package com.medprompt.screens

import android.content.Intent

import androidx.compose.foundation.layout.*
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.*
import com.medprompt.components.Button
import com.medprompt.components.DrawerBody
import com.medprompt.components.DrawerHeader
import com.medprompt.components.MenuItem
import com.medprompt.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        content = {
//            Row {
//            TODO: Implement Nathan's changes to mine
//                AppHeader()
            AppointmentList(navController)
//            }
        },
        floatingActionButton = { AddButton(navController) },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun AppointmentList(navController: NavController) {
    val context = LocalContext.current
    lateinit var auth: FirebaseAuth;
    var firebaseUser: FirebaseUser? = null
    lateinit var firestore : FirebaseFirestore

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "login",
                        title = "Login",
                        contentDescription = "Login in using your account",
                        icon = Icons.Default.Person
                    ),
                    MenuItem(
                        id = "account",
                        title = "My Account",
                        contentDescription = "Manage my account",
                        icon = Icons.Default.AccountCircle
                    ),
                    MenuItem(
                        id = "signout",
                        title = "Sign Out",
                        contentDescription = "Sign out",
                        icon = Icons.Default.ArrowBack
                    ),
                ),
                onItemClick = {
                    if (it.id == "login") {
                        context.startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            )
        },
        content = {
            Column {
                AppItem(appText = "Complete Diet Form", appDate = "TODAY, 9:00 PM")
                AppItem(appText = "Inflectra Treatment", appDate = "MAY 12, 10:00 PM")
                AppItem(appText = "Take Methotrexate", appDate = "MAY 12, 9:00 PM")
                AppItem(appText = "Refill Methotrexate", appDate = "AUGUST 12")
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
        HomeScreen(navController = rememberNavController())
    }
}
