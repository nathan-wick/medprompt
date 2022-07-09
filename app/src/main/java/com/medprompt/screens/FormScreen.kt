package com.medprompt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.components.*
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun FormScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderOptions(navController = navController, contextLabel = "Form")

        Text(text = "Form Name")
        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {
            var formName by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = formName,
                onValueChange = { formName = it }
            )
        }

        Row() {
            DateTimePicker(label = "Date and Time to complete Form ")
        }

        Text(text = "Frequency to complete Form")
        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {
            val freqList = listOf("Weeks", "Months", "Years")

            InputField(weight = 3f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DropDown(weight = 3f, items = freqList)
        }

        // TODO: Make this a list of dynamic questions
        Text(text = "Question 1")
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .padding(5.dp)
                .height(50.dp)) {
                InputField(weight = 1f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add Question")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreen_Preview() {
    MedpromptTheme {
        FormScreen(navController = rememberNavController())
    }
}