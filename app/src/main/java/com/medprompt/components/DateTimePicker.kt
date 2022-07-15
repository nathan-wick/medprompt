package com.medprompt.components

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.buttonHeight
import com.medprompt.ui.theme.defaultPadding

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
fun DateTimePicker (label: String) {
    Column {
        Text(text = label)
        Row(modifier = Modifier
            .padding(5.dp)
            .height(60.dp)) {

            // These lists are static data for now... Firebase will come later
            val monthList = listOf("Jan", "Feb")
            val yearList = listOf("2000", "2001", "2002")

            InputField(weight = 3f, placeholder = "Day", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            DropDown(weight = 3f, items = monthList)
            DropDown(weight = 3f, items = yearList)
        }

        Row(modifier = Modifier
            .padding(5.dp)
            .height(50.dp)) {
            val context = LocalContext.current // this is needed to provide context within the scope of the onclick function
            var formattedTime by remember { mutableStateOf("")} // this is needed to remember the state of the selected time after formatting
            Button(
                onClick = {

                    val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
                        object : TimePickerDialog.OnTimeSetListener {
                            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                                // this formatting code implemented from https://www.geeksforgeeks.org/time-picker-dialog-in-android/
                                // logic to properly handle the picked timings by user
                                formattedTime = when {
                                    hourOfDay == 0 -> {
                                        if (minute < 10) {
                                            "${hourOfDay + 12}:0${minute} am"
                                        } else {
                                            "${hourOfDay + 12}:${minute} am"
                                        }
                                    }
                                    hourOfDay > 12 -> {
                                        if (minute < 10) {
                                            "${hourOfDay - 12}:0${minute} pm"
                                        } else {
                                            "${hourOfDay - 12}:${minute} pm"
                                        }
                                    }
                                    hourOfDay == 12 -> {
                                        if (minute < 10) {
                                            "${hourOfDay}:0${minute} pm"
                                        } else {
                                            "${hourOfDay}:${minute} pm"
                                        }
                                    }
                                    else -> {
                                        if (minute < 10) {
                                            "${hourOfDay}:${minute} am"
                                        } else {
                                            "${hourOfDay}:${minute} am"
                                        }
                                    }
                                }
                            }
                        }
                    val timePicker: TimePickerDialog = TimePickerDialog(
                        // pass the Context
                        context,
                        // listener to perform task when time is picked
                        timePickerDialogListener,
                        // default hour when the time picker dialog is opened
                        12,
                        // default minute when the time picker dialog is opened
                        0,
                        // 24 hours time picker is false (varies according to the region)
                        false
                    )

                    // then after building the timepicker dialog show the dialog to user
                    timePicker.show()
                },

                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue200),
                modifier = Modifier
                    .padding(horizontal = defaultPadding)
                    .height(buttonHeight)
                    .weight(weight = 1f)
            ) {

                if (formattedTime == ""){
                    Text(text = "Select a time") // displays if the formattedTime has not been instantiated
                }else{
                    Text(text = formattedTime) // displays the selected time after a time has been selected
                }
                // This is managed under the assumption that formattedString has no value, once it has a value (i.e. a time is selected),
                // that time will be displayed in this text field.
            }

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