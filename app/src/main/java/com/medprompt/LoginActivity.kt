package com.medprompt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.components.Button
import com.medprompt.dto.User
import com.medprompt.ui.theme.MedpromptTheme
import com.medprompt.ui.theme.defaultPadding

class LoginActivity: ComponentActivity() {
    private lateinit var auth: FirebaseAuth;
    private var firebaseUser: FirebaseUser? = null
    private lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedpromptTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    signIn()
                }
            }
        }
    }

    fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
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
            firebaseUser = auth.currentUser
            firebaseUser?.let{
                val user = User(it.uid, it.displayName)
                saveUser(user)
            }
        } else{
            Log.e("MainActivity.kt", "Error logging in" + response?.error?.errorCode)
        }
    }

    fun saveUser(user : User){
        user?.let{
                user ->
            val handle = firestore.collection("users").document(user.uid).set(user)
            handle.addOnSuccessListener { Log.d("Firebase", "Document Saved")}
            handle.addOnFailureListener { Log.e("Firebase", "Save failed $it")}
        }
    }
}