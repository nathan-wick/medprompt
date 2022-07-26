package com.medprompt.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.*
import com.medprompt.ui.*

/**
 * A custom component for our text boxes. Here we can customize everything about them.
 * NOTE: This component has to be inside a Row
 */
@Composable
fun RowScope.InputField (weight: Float, keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), placeholder: String = "", value: String= "", onValueChange: (String) -> Unit = { /*TODO onClick*/ }) {
    Box(
        modifier = Modifier
            .height(55.dp)
            .padding(horizontal = 5.dp)
            .weight(weight)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            placeholder = { Text(text = placeholder) },
            shape = CircleShape,
            modifier = Modifier
                .fillMaxSize()
                .border(width = 4.dp, color = Blue200, shape = CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputField_Preview() {
    MedpromptTheme {
        Row() {
            InputField(weight = 3f, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        }
    }
}