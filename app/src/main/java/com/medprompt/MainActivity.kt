package com.medprompt

import android.os.Bundle
import android.os.TestLooperManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.MedpromptTheme
// we should be able to use retrofit to get json for medication names

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedpromptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        content = { AppointmentList() },
        floatingActionButton = { AddButton() }
    )
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
        color = com.medprompt.ui.theme.Silver100
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = appText, color = Color.Black, modifier = Modifier.padding(horizontal = 10.dp))
            Text(text = appDate, color = com.medprompt.ui.theme.Blue200, modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}

@Composable
fun AddButton () {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Appointments",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MedpromptTheme {
        HomeScreen()
    }
}
