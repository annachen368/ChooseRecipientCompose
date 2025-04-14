package com.example.chooserecipientcompose.ui.chooserecipient

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.chooserecipientcompose.domain.model.Recipient

@Composable
fun ChooseRecipientScreen(
    uiState: ChooseRecipientViewModel.UiState,
    innerPadding: PaddingValues,
    loadContacts: () -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {},
) {
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

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            loadContacts()
        } else {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    if (hasPermission) {
        when (uiState) {
            is ChooseRecipientViewModel.UiState.Loading -> {
                // Show loading indicator
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ChooseRecipientViewModel.UiState.Success -> {
                // Show the list of recipients
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = { onSearchQueryChanged(it) },
                        label = { Text("Search") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    ContactList(uiState.filteredContacts)
                }
            }

            is ChooseRecipientViewModel.UiState.Error -> {
                // Show error message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    } else {
        // Show a loading state, permission explanation, or fallback UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Permission to access contacts is required.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ContactList(contacts: List<Recipient>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(contacts.size) { index ->
            val recipient = contacts[index]
            // Display recipient information
            // For example, you can use a Text composable to show the recipient's name
            Text(text = recipient.displayName)
            Text(text = recipient.token)
            Text(text = recipient.tokenType)
            Text(text = recipient.recipientTokenStatus)
            if (index < contacts.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChooseRecipientScreenPreview() {
    val uiState = ChooseRecipientViewModel.UiState.Success(
        serverContacts = listOf(
            Recipient(
                recipientId = "1",
                displayName = "John Doe",
                token = "token1",
                tokenType = "type1",
                recipientTokenStatus = "active",
                photoUri = "123"
            ),
            Recipient(
                recipientId = "2",
                displayName = "Jane Smith",
                token = "token2",
                tokenType = "type2",
                recipientTokenStatus = "inactive",
                photoUri = "123"
            )
        ),
        deviceContacts = listOf(
            Recipient(
                recipientId = "3",
                displayName = "Alice Johnson",
                token = "token3",
                tokenType = "type3",
                recipientTokenStatus = "active",
                photoUri = "123"
            ),
            Recipient(
                recipientId = "4",
                displayName = "Bob Brown",
                token = "token4",
                tokenType = "type4",
                recipientTokenStatus = "inactive",
                photoUri = "123"
            )
        )
    )
    ChooseRecipientScreen(uiState, PaddingValues(16.dp))
}