package com.medprompt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.medprompt.ui.theme.MedpromptTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var appState: AppState
    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener
    private companion object { private const val CHANNEL_ID = "channel01" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {
                Toast.makeText(this@MainActivity, "Welcome back ${user.email}", Toast.LENGTH_LONG).show()
            }
        }

        setContent {
            MedpromptTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    appState = rememberAppState()
                    SetupNavGraph(appState = appState)
                }
            }
        }
    }

    @Composable
    fun rememberAppState(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        navController: NavHostController = rememberNavController(),
        scope: CoroutineScope = rememberCoroutineScope()
    ) = remember(scaffoldState, navController, scope) {
        AppState(scaffoldState, navController, scope)
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        auth.removeAuthStateListener(authStateListener)
        super.onStop()
    }

    fun showNotification(){
        createNotificationChannel()
        val date= Date()
        val notificationID=SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        val mainIntent= Intent(this, MainActivity::class.java)
        mainIntent.putExtra("MESSAGE", "You Have An Alert")

        mainIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent= PendingIntent.getActivity(this, 1,mainIntent, PendingIntent.FLAG_IMMUTABLE)


        val notificationBuilder=NotificationCompat.Builder(this, CHANNEL_ID)
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
        val name: CharSequence="MyNotification"
        val description="My Notification channel description"
        val importance= NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel=NotificationChannel(CHANNEL_ID, name, importance)
        notificationChannel.description=description
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}