package com.medprompt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.medprompt.ui.theme.MedpromptTheme

/**
 * class HomeActivity is the Home page of all the activities.
 * Uses MedpromptTheme and calls ToDoItemFacts()
 */
public class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedpromptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ToDoItemFacts("Android")
                }
            }
        }
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundle:Bundle? = intent!!.extras

        if(bundle !=null){
            val message=bundle.getString("MESSAGE")
            Toast.makeText(this, "$message", Toast.LENGTH_LONG).show()
        }
    }
}

/**
 * Display OutlinedtextField for mutable itemName, timeOfDay, frequency, doesSize, stock.
 * Create a "Save" onClick button
 */
@Composable
fun ToDoItemFacts(name: String) {
    var itemName by remember { mutableStateOf("")}
    var timeOfDay by remember { mutableStateOf("")}
    var frequency by remember { mutableStateOf("")}
    var doseSize by remember { mutableStateOf("")}
    var stock by remember { mutableStateOf("")}
    val context = LocalContext.current

    Column {
        OutlinedTextField(value = itemName,
            onValueChange = { itemName = it },
            label = { Text(stringResource(R.string.itemName)) }
        )
        OutlinedTextField(value = timeOfDay,
            onValueChange = { timeOfDay = it },
            label = { Text(stringResource(R.string.timeTakeMedication)) }
        )
        OutlinedTextField(value = frequency,
            onValueChange = { frequency = it },
            label = { Text(stringResource(R.string.frequency)) }
        )
        OutlinedTextField(value = doseSize,
            onValueChange = { doseSize = it },
            label = { Text(stringResource(R.string.doseSize)) }
        )
        OutlinedTextField(value = stock,
            onValueChange = { stock = it },
            label = { Text(stringResource(R.string.stock)) }
        )
        Button (
            onClick = {
                Toast.makeText(
                    context,
                    "$itemName $timeOfDay $frequency $doseSize $stock",
                    Toast.LENGTH_LONG
                ).show()
            }
        ){
            Text(text = stringResource(R.string.save))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MedpromptTheme {
        ToDoItemFacts(name = "")
    }
}
