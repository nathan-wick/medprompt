package com.medprompt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.components.*
import com.medprompt.ui.theme.MedpromptTheme

// global values
import com.medprompt.ui.theme.defaultPadding
import com.medprompt.ui.theme.inputFieldHeight

@Composable
fun FormScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderOptions(navController = navController, contextLabel = "Form")

        Text(text = "Form Name")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(inputFieldHeight)) {

            InputField(weight = 1f)
        }

        DateTimePicker(label = "Date and Time to complete Form ")

        Text(text = "Frequency to complete Form")
        Row(modifier = Modifier
            .padding(defaultPadding)
            .height(inputFieldHeight)) {
            val freqList = listOf("Weeks", "Months", "Years")

            InputField(weight = 3f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DropDown(weight = 3f, items = freqList)
        }

        // TODO: Make this a list of dynamic questions
        Text(text = "Question 1")
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .padding(defaultPadding)
                .height(inputFieldHeight)) {
                InputField(weight = 1f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
            }
            
            Row(modifier = Modifier
                .padding(defaultPadding)) {
                Button(weight = 1f, text = "Add Question")
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