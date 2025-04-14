package com.example.chooserecipientcompose.ui.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chooserecipientcompose.ui.theme.MyTemplateTheme

@Composable
fun WelcomeScreen(
    innerPadding: PaddingValues,
    state: WelcomeViewModel.UiState,
    navigateToChooseRecipient: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.Center
    ) {
        when (state) {
            is WelcomeViewModel.UiState.Loading -> {
                // Show loading indicator
                CircularProgressIndicator()
            }

            is WelcomeViewModel.UiState.Success -> {
                Text(text = state.message)
                Button(onClick = {
                    navigateToChooseRecipient()
                }) {
                    Text(text = "Go to Choose Recipient")
                }
            }

            is WelcomeViewModel.UiState.Error -> {
                Text(text = state.message, color = androidx.compose.ui.graphics.Color.Red)
            }
        }
    }
}


// Type @prev auto generate
@Preview
@Composable
private fun WelcomeScreenPreview() {
    MyTemplateTheme {
        WelcomeScreen(
            innerPadding = PaddingValues(0.dp),
            state = WelcomeViewModel.UiState.Success("Welcome to the app!"),
            navigateToChooseRecipient = {}
        )
    }
}