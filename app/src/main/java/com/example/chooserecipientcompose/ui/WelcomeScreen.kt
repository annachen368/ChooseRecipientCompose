package com.example.chooserecipientcompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WelcomeScreen(innerPadding: PaddingValues, viewModel: WelcomeViewModel = hiltViewModel()) {
    val title = viewModel.title.value
    Text(text = title)
}