package com.medprompt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medprompt.ui.theme.MedpromptTheme

@Composable
fun RowScope.DropDown (weight: Float, items: List<String>) {
    var selectedIndex by remember { mutableStateOf(0) }
    var isOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.height(50.dp).weight(weight = weight)) {
        Text(
            items[selectedIndex],
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { isOpen = true })
                .background(Color.Gray))
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