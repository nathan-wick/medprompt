package com.medprompt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.Screen
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.components.Button

import com.medprompt.ui.theme.defaultPadding

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.height(350.dp), contentAlignment = Alignment.Center) {
            Text(text = "MedPrompt", fontWeight = FontWeight.Bold, fontSize = 35.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }

        Box(modifier = Modifier.padding(5.dp), contentAlignment = Alignment.Center) {
            Text(text = "Sign In With", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        Row (modifier = Modifier.padding(defaultPadding)) {
            Button(text = "Email/Password")
        }
        Row (modifier = Modifier.padding(defaultPadding)) {
            Button(text = "Google")
        }
        Row (modifier = Modifier.padding(defaultPadding)) {
            Button(text = "Continue Without Signing In") {
                navController.navigate(Screen.Home.route)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreen_Preview() {
    MedpromptTheme {
        LoginScreen(navController = rememberNavController())
    }
}