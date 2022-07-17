package com.medprompt

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val Title: String,
    val ContentDescription: String,
    val Icon: ImageVector
)