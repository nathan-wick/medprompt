package com.medprompt.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun RowScope.InputField (weight: Float, keyboardOptions: KeyboardOptions, placeholder: String = "") {
    var text by remember { mutableStateOf("") }

    Box(modifier = Modifier.weight(weight), contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            placeholder = { Text(text = placeholder)}
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