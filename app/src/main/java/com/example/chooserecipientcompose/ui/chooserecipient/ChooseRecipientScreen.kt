package com.example.chooserecipientcompose.ui.chooserecipient

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chooserecipientcompose.domain.model.Recipient

@Composable
fun ChooseRecipientScreen(viewModel: ChooseRecipientViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasPermission = isGranted
        if (isGranted) {
            // Load or show contacts
        } else {
            // Show a message or fallback
        }
    }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    if (hasPermission) {
        viewModel.getServerRecipientsAndDeviceContacts()
        when (val uiState = viewModel.uiState.value) {
            is ChooseRecipientViewModel.UiState.Loading -> {
                // Show loading indicator
            }

            is ChooseRecipientViewModel.UiState.Success -> {
                // Show the list of recipients
                ContactList(uiState.serverContacts + uiState.deviceContacts)
            }

            is ChooseRecipientViewModel.UiState.Error -> {
                // Show error message
            }
        }
    } else {
        // Show a loading state, permission explanation, or fallback UI
    }
}

@Composable
fun ContactList(contacts: List<Recipient>) {
    LazyColumn {
        items(contacts.size) { index ->
            val recipient = contacts[index]
            // Display recipient information
            // For example, you can use a Text composable to show the recipient's name
            Text(text = recipient.token)
        }
    }
}