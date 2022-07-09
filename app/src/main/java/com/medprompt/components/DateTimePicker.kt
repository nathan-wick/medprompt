package com.medprompt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun DateTimePicker (label: String) {
    Column {
        Text(text = label)
        Row(modifier = Modifier.padding(5.dp).height(60.dp)) {
            val monthList = listOf("Jan", "Feb")
            val yearList = listOf("2000", "2001", "2002")

            InputField(weight = 3f, placeholder = "Day", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DropDown(weight = 3f, items = monthList)
            DropDown(weight = 3f, items = yearList)
        }

        Row(modifier = Modifier.padding(5.dp).height(50.dp)) {
            val timeList = listOf("8:00", "9:00")
            val pmAmList = listOf("PM", "AM")

            DropDown(weight = 1f, items = timeList)
            DropDown(weight = 1f, items = pmAmList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicationScreen_Preview() {
    MedpromptTheme {
        DateTimePicker(label = "Label in context here...")
    }
}