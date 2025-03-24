package com.example.chooserecipientcompose.ui.welcome

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WelcomeScreen(innerPadding: PaddingValues, viewModel: WelcomeViewModel = hiltViewModel()) {
    when (val state = viewModel.uiState.value) {
        is WelcomeViewModel.UiState.Loading -> {
            // Show loading indicator
            CircularProgressIndicator()
        }
        is WelcomeViewModel.UiState.Success -> {
            Text(text = state.message)
        }
        is WelcomeViewModel.UiState.Error -> {
            Text(text = state.message, color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}