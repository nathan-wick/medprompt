package com.medprompt.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.medprompt.Screen
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun HeaderOptions (navController: NavController, contextLabel: String) {
    Row(modifier = Modifier
        .padding(5.dp)
        .height(50.dp)) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp), onClick = {
                navController.navigate(route = Screen.Home.route)
            }) {
            Text(text = "Cancel")
        }
        Button(modifier = Modifier
            .weight(2f)
            .padding(5.dp), onClick = { /*TODO*/ }) {
            // TODO: This has to say "Update Medication" if updating
            Text(text = "Add $contextLabel")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderOptions_Preview() {
    MedpromptTheme {
        HeaderOptions(navController = rememberNavController(), contextLabel = "Context Label")
    }
}