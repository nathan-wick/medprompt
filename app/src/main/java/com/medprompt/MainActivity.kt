package com.medprompt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.medprompt.ui.theme.MedpromptTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// we should be able to use retrofit to get json for medication names

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedpromptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                    startingScreen()
                }
            }
        }
    }


    @Composable
    private fun startingScreen() {
        Button(onClick = {signIn()}) {Text(text = stringResource(R.string.Logon))}
    }

    private fun signIn() {
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        val signinIntent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build()

        signInLauncher.launch(signinIntent)
    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
        res -> this.signInResult(res)
    }

    private fun signInResult(result: FirebaseAuthUIAuthenticationResult){
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK){
            user = FirebaseAuth.getInstance().currentUser
        } else{
            Log.e("MainActivity.kt", "Error logging in" + response?.error?.errorCode)
        }
    }
}