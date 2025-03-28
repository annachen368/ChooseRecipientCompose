package com.example.chooserecipientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chooserecipientcompose.ui.chooserecipient.ChooseRecipientScreen
import com.example.chooserecipientcompose.ui.theme.MyTemplateTheme
import com.example.chooserecipientcompose.ui.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTemplateTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Use Jetpack Navigation component to navigate between screens
                    NavHost(navController, startDestination = "welcome") {
                        // Define your navigation graph here
                        // For example, you can add a composable for the welcome screen
                        composable("welcome") {
                            WelcomeScreen(
                                innerPadding = innerPadding,
                                navigateToChooseRecipient = {
                                    // Handle navigation to Choose Recipient screen
                                    // Use Jetpack Navigation component
                                    navController.navigate("choose_recipient")
                                }
                            )
                        }
                        composable("choose_recipient") {
                            // Handle navigation to Choose Recipient screen
                            // Use Jetpack Navigation component
                            ChooseRecipientScreen(innerPadding = innerPadding)
                        }
                    }
                }
            }
        }
    }
}