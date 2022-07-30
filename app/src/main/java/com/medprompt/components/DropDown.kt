package com.medprompt.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme

/**
 * Custom Component for our DropDowns, which are used often.
 */
@Composable
fun RowScope.DropDown (weight: Float, items: List<String>, selectedValue: String, onSelectedValue: (String) -> Unit = {}) {
    var selectedItem by remember { mutableStateOf(items[ items.indexOf(selectedValue) ]) }
    var isOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(55.dp)
            .weight(weight = weight)
            .border(width = 4.dp, color = Blue200, shape = CircleShape)
            .clickable { isOpen = !isOpen },
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = selectedItem,
                textAlign = TextAlign.Center,
            )

            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Open Dropdown Icon")
            DropdownMenu(
                expanded = isOpen,
                onDismissRequest = { isOpen = false },
                content = {
                    items.forEach { item ->
                        DropdownMenuItem(
                            content = { Text(text = item, textAlign = TextAlign.Center) },
                            onClick = {
                                selectedItem = item
                                isOpen = false
                                onSelectedValue.invoke(item)
                            }
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDown_Preview() {
    var test by remember { mutableStateOf("test") }
    MedpromptTheme {
        Row() {
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"), selectedValue = test, onSelectedValue = {  test = it })
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"), selectedValue = test,  onSelectedValue = {  test = it })
        }
    }
}