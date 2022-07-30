package com.medprompt.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.buttonHeight
import com.medprompt.ui.theme.defaultPadding

/**
 * A custom component for our Buttons. Here we can customize everything about them.
 * NOTE: This component has to be inside a Row
 */
@Composable
fun RowScope.Button (weight: Float = 1f, text: String, onClick:() -> Unit = { /* TODO onClick */ }) {

    // the name for our custom button is the same...
    androidx.compose.material.Button(
        content = { Text(text = text) },
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Blue200),
        modifier = Modifier
            .padding(horizontal = defaultPadding)
            .height(buttonHeight)
            .weight(weight = weight)
    )
}

@Preview(showBackground = true)
@Composable
fun Button_Preview() {
    MedpromptTheme {
        Column() {
            Row() {
                Button(weight = 3f, text = "Custom Button")
            }
        }
    }
}