package com.example.zwigato

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zwigato.service.NotificationService
import com.example.zwigato.ui.theme.ZwigatoTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<OrderViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZwigatoTheme {
                val context = LocalContext.current
                val notificationManager =
                    context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                LiveNotificationManager.initialize(
                    context.applicationContext,
                    notificationManager
                )
                val orderStatus = viewModel.orderState.collectAsState().value

                LaunchedEffect(orderStatus) {
                    val intent = Intent(context, NotificationService::class.java).apply {
                        putExtra("ORDER_DATA", orderStatus)
                    }
                    ContextCompat.startForegroundService(context,intent)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZwigatoTheme {
        Greeting("Android")
    }
}