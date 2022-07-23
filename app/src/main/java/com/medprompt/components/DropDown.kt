package com.medprompt.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.window.PopupProperties
import com.medprompt.ui.theme.Blue200
import com.medprompt.ui.theme.MedpromptTheme
import kotlinx.coroutines.NonDisposableHandle.parent

/**
 * Custom Component for our DropDowns, which are used often.
 */
@Composable
fun RowScope.DropDown (weight: Float, items: List<String>) {
    var selectedIndex by remember { mutableStateOf(0) }
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
                text = items[selectedIndex],
                textAlign = TextAlign.Center
            )

            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(
                expanded = isOpen,
                onDismissRequest = { isOpen = false }
            ) {
                items.forEachIndexed { index, itemText ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        isOpen = false
                    }) {
                        Text(text = itemText, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDown_Preview() {
    MedpromptTheme {
        Row() {
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"))
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"))
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"))
            DropDown(weight = 3f, items = listOf("Item 1", "Item 2", "etc"))
        }
    }
}