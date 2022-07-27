package com.medprompt.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.dto.CustomDateTime
import com.medprompt.ui.theme.MedpromptTheme
import java.util.*

/**
 * This is custom Date and Time picker. Why create this?
 * Well, we're using this in 90% of our screens and I (Alex)
 * wanted to isolate all the date and time logic here because
 * it's going to be easier down the road to handle the edge cases
 * of dates.
 *
 * We need to think about edge cases for our dates.
 * For example, Feb is a month that sometimes has 29 days,
 * but most time it has 28 days.
 *
 * This is only only reason why I wanted to isolate this part of the app.
 */
@Composable
fun DateTimePicker (label: String, onSelectedValue: (CustomDateTime) -> Unit = {}) {
    val context = LocalContext.current
    val monthList = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val timeList = listOf("1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30")
    val pmAmList = listOf("PM", "AM")

    val yearList = mutableListOf<String>()

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    var yearToAdd = currentYear
    val availableYears = 10
    while (yearToAdd < (currentYear + availableYears)) {
        yearList.add(yearToAdd.toString())
        yearToAdd = yearToAdd + 1
    }

    var day by remember { mutableStateOf(1) }
    var month by remember { mutableStateOf(monthList[ Calendar.getInstance().get(Calendar.MONTH) - 1]) }
    var year by remember { mutableStateOf( currentYear.toString() ) }
    var hour by remember { mutableStateOf(timeList[0]) }
    var amPm by remember { mutableStateOf(pmAmList[0]) }

    val dateTime by remember {
        mutableStateOf(CustomDateTime(
            year = year,
            month = month,
            day = day.toString(),
            hour = hour,
            pmOrAm = amPm
        ))
    }

    onSelectedValue.invoke(dateTime)

    Column {
        Text(text = label)
        Row(modifier = Modifier
            .padding(5.dp)
            .height(60.dp)) {

            InputField(
                weight = 3f, placeholder = "Day",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = day.toString(),
                onValueChange = {
                    if (it.isNullOrBlank() || it.isNullOrEmpty() || it.toInt() > 31) {
                        day = 1
                        Toast.makeText(context, "Must be between 0 and 31", Toast.LENGTH_LONG).show()
                    } else {
                        day = it.toInt()
                    }
                }
            )
            DropDown(
                weight = 3f,
                items = monthList,
                onSelectedValue = { month = it }
            )
            DropDown(
                weight = 3f,
                items = yearList,
                onSelectedValue = { year = it }
            )
        }

        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {

            DropDown(
                weight = 1f,
                items = timeList,
                onSelectedValue = { hour = it }
            )
            DropDown(
                weight = 1f,
                items = pmAmList,
                onSelectedValue = { amPm = it }
            )
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