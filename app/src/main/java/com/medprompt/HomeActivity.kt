package com.medprompt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import com.medprompt.dto.Medication
import com.medprompt.ui.theme.MedpromptTheme

/**
 * class HomeActivity is the Home page of all the activities.
 * Uses MedpromptTheme and calls ToDoItemFacts()
 */
public class HomeActivity : ComponentActivity() {
    private var strSelectedData: String = ""
    private var selectedMedicine: Medication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedpromptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ToDoItemFacts()
                }
            }
        }
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundle: Bundle? = intent!!.extras

        if (bundle != null) {
            val message = bundle.getString("MESSAGE")
            Toast.makeText(this, "$message", Toast.LENGTH_LONG).show()
        }
    }


    /**
     * Display OutlinedtextField for mutable itemName, timeOfDay, frequency, doesSize, stock.
     * Create a "Save" onClick button
     */
    @Composable
    fun ToDoItemFacts() {
        var itemName by remember { mutableStateOf("") }
        var timeOfDay by remember { mutableStateOf("") }
        var frequency by remember { mutableStateOf("") }
        var doseSize by remember { mutableStateOf("") }
        var stock by remember { mutableStateOf("") }
        val context = LocalContext.current

        Column {
            TextFieldWithDropdownUsage(dataIn = ArrayList<Medication>(), stringResource(R.string.MedName))
            OutlinedTextField(value = itemName,
                onValueChange = { itemName = it },
                label = { Text(stringResource(R.string.itemName)) }
            )
            OutlinedTextField(value = timeOfDay,
                onValueChange = { timeOfDay = it },
                label = { Text(stringResource(R.string.timeTakeMedication)) }
            )
            OutlinedTextField(value = frequency,
                onValueChange = { frequency = it },
                label = { Text(stringResource(R.string.frequency))},
                modifier = Modifier.width(IntrinsicSize.Min)
            )
            OutlinedTextField(value = doseSize,
                onValueChange = { doseSize = it },
                label = { Text(stringResource(R.string.doseSize)) }
            )
            OutlinedTextField(value = stock,
                onValueChange = { stock = it },
                label = { Text(stringResource(R.string.stock)) }
            )
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "$itemName $timeOfDay $frequency $doseSize $stock",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }

    @Composable
    fun TextFieldWithDropdownUsage(dataIn: List<Medication>, label: String ="") {

        val dropDownOptions = remember { mutableStateOf(listOf<Medication>()) }
        val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
        val dropDownExpanded = remember { mutableStateOf(false) }

        fun onDropdownDismissRequest() {
            dropDownExpanded.value = false
        }

        fun onValueChanged(value: TextFieldValue) {
            strSelectedData = value.text
            dropDownExpanded.value = true
            textFieldValue.value = value
            dropDownOptions.value = dataIn.filter {
                it.toString().startsWith(value.text) && it.toString() != value.text
            }
        }

        TextFieldWithDropdown(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue.value,
            setValue = ::onValueChanged,
            onDismissRequest = ::onDropdownDismissRequest,
            dropDownExpanded = dropDownExpanded.value,
            list = dropDownOptions.value,
            label = label
        )
    }

    @Composable
    fun TextFieldWithDropdown(
        modifier: Modifier = Modifier,
        value: TextFieldValue,
        setValue: (TextFieldValue) -> Unit,
        onDismissRequest: () -> Unit,
        dropDownExpanded: Boolean,
        list: List<Medication>,
        label: String = ""
    ) {
        Box(modifier) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused)
                            onDismissRequest()
                    },
                value = value,
                onValueChange = setValue,
                label = { Text(label) },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            DropdownMenu(
                expanded = dropDownExpanded,
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                onDismissRequest = onDismissRequest
            ) {
                list.forEach { text ->
                    DropdownMenuItem(onClick = {
                        setValue(
                            TextFieldValue(
                                text.toString(),
                                TextRange(text.toString().length)
                            )
                        )
                        selectedMedicine = text
                    }) {
                        Text(text = text.toString())
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview2() {
        MedpromptTheme {
            ToDoItemFacts()
        }
    }
}