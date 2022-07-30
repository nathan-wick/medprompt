package com.medprompt

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

class AppState (
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val scope: CoroutineScope
)