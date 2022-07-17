package com.medprompt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.*
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.NavigationDrawerComposeTheme
import com.medprompt.ui.theme.Silver100
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        content = {
//            Row {
//            TODO: Implement Nathan's changes to mine
//                AppHeader()
            AppointmentList()
//            }
        },
        floatingActionButton = { AddButton(navController) }
    )
}

@Composable
fun AppHeader() {
    NavigationDrawerComposeTheme {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold (
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(
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
                            Title = "Home",
                            ContentDescription = "Go to home screen",
                            Icon = Icons.Default.Home
                        ),
                        MenuItem(
                            id = "account",
                            Title = "My Account",
                            ContentDescription = "Manage my account",
                            Icon = Icons.Default.AccountCircle
                        ),
                        MenuItem(
                            id = "signout",
                            Title = "Sign Out",
                            ContentDescription = "Sign out",
                            Icon = Icons.Default.ArrowBack
                        ),
                    ),
                    onItemClick = {
                        // TODO: Change screen on item click
                        println("Clicked on ${it.Title}")
                    }
                )
            }
        ) {

        }
    }
}

@Composable
fun AppointmentList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .width(350.dp)
                .height(50.dp),
            color = MaterialTheme.colors.primary
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(text = "MedPrompt")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }

        AppItem(appText = "Complete Diet Form", appDate = "TODAY, 9:00 PM")
        AppItem(appText = "Inflectra Treatment", appDate = "MAY 12, 10:00 PM")
        AppItem(appText = "Take Methotrexate", appDate = "MAY 12, 9:00 PM")
        AppItem(appText = "Refill Methotrexate", appDate = "AUGUST 12")

    }
}

@Composable
fun AppItem(appText : String, appDate: String) {
    Surface(
        modifier = Modifier
            .width(350.dp)
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
            Box(modifier = Modifier.width(150.dp)) {
                Column (
                    horizontalAlignment = Alignment.End
                ) {
                    Button(onClick = {
                        navController.navigate(route = Screen.Medication.route)
                    }) {
                        Text(text = "Medication")
                    }
                    Button(onClick = {
                        navController.navigate(route = Screen.Appointment.route)
                    }) {
                        Text(text = "Appointment")
                    }
                    Button(onClick = {
                        navController.navigate(route = Screen.Form.route)
                    }) {
                        Text(text = "Form")
                    }
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
