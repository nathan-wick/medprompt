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
import com.medprompt.ui.theme.buttonHeight
import com.medprompt.ui.theme.defaultPadding

@Composable
fun HeaderOptions (navController: NavController, contextLabel: String, addButtonOnClick: () -> Unit = { /*TODO onClick*/ }, showDeleteButton: Boolean = false, deleteButtonOnCick: () -> Unit = { /*TODO onClick*/ }) {
    Row(modifier = Modifier
        .height(buttonHeight)
        .padding(defaultPadding)) {
        Button(
            weight = 1f,
            text = "Cancel",
            onClick = {
            navController.navigate(route = Screen.Home.route)
        })
        Button(
            weight = 2f,
            text = contextLabel,
            onClick = addButtonOnClick
        )

        if (showDeleteButton) {
            Button(
                weight = 1f,
                text = "Delete",
                onClick = deleteButtonOnCick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderOptions_Preview() {
    MedpromptTheme {
        HeaderOptions(navController = rememberNavController(), contextLabel = "Context Label", showDeleteButton = true)
    }
}