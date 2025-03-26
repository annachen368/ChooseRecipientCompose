package com.example.chooserecipientcompose.ui.chooserecipient

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChooseRecipientScreen(viewModel: ChooseRecipientViewModel = hiltViewModel()) {
    when (val uiState = viewModel.uiState.value) {
        is ChooseRecipientViewModel.UiState.Loading -> {
            // Show loading indicator
        }

        is ChooseRecipientViewModel.UiState.Success -> {
            // Show the list of recipients
            val recipients = uiState.serverContacts + uiState.deviceContacts
            LazyColumn {
                items(recipients.size) { index ->
                    val recipient = recipients[index]
                    // Display recipient information
                    // For example, you can use a Text composable to show the recipient's name
                    Text(text = recipient.token)
                }
            }
        }

        else -> {
            // Show error message
        }
    }
}