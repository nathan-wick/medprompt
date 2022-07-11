package com.medprompt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.PopupProperties
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.medprompt.ui.theme.MedpromptTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.medprompt.dto.Medication
import com.medprompt.dto.User
import java.text.SimpleDateFormat
import java.util.*

// we should be able to use retrofit to get json for medication names

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private var firebaseUser: FirebaseUser? = null
    private lateinit var firestore : FirebaseFirestore
    private companion object{
        private const val CHANNEL_ID = "channel01"
    }
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
            firebaseUser = FirebaseAuth.getInstance().currentUser
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
            handle.addOnFailureListener {Log.e("Firebase", "Save failed $it")}
        }
    }
    fun showNotification(){
        createNotificationChannel()
        val date= Date()
        val notificationID=SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        val mainIntent= Intent(this, HomeActivity::class.java)
        mainIntent.putExtra("MESSAGE", "You Have An Alert")

        mainIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent= PendingIntent.getActivity(this, 1,mainIntent, PendingIntent.FLAG_IMMUTABLE)


        val notificationBuilder=NotificationCompat.Builder(this,"$CHANNEL_ID")
        notificationBuilder.setSmallIcon(R.drawable.ic_notification)
        notificationBuilder.setContentTitle("Notification Title")
        notificationBuilder.setContentText("This is the multi line description")
        notificationBuilder.priority=NotificationCompat.PRIORITY_DEFAULT
        notificationBuilder.setAutoCancel(true)

        notificationBuilder.setContentIntent(mainPendingIntent)


        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(notificationID, notificationBuilder.build())
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name: CharSequence="MyNotification"
            val description="My Notification channel description"
            val importance= NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel=NotificationChannel(CHANNEL_ID, name, importance)
            notificationChannel.description=description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

}