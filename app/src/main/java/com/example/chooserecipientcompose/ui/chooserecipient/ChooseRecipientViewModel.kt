package com.example.chooserecipientcompose.ui.chooserecipient

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chooserecipientcompose.domain.model.Recipient
import com.example.chooserecipientcompose.domain.usecase.GetCustomerProfileUseCase
import com.example.chooserecipientcompose.domain.usecase.GetDeviceContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseRecipientViewModel @Inject constructor(
    private val getCustomerProfileUseCase: GetCustomerProfileUseCase,
    private val getDeviceContactUseCase: GetDeviceContactUseCase
) :
    ViewModel() {
    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val serverContacts: List<Recipient>,
            val deviceContacts: List<Recipient>,
            val searchQuery: String = "",
            val filteredContacts: List<Recipient> = serverContacts + deviceContacts
        ) : UiState()

        data class Error(val message: String) : UiState()
    }

    private val _uiState = mutableStateOf<UiState>(UiState.Loading)
    val uiState = _uiState

    fun getServerRecipientsAndDeviceContacts() {
        viewModelScope.launch {
            try {
                val customerProfile = getCustomerProfileUseCase.getCustomerProfile()
                val recipients = customerProfile.recipients
                val deviceContacts = getDeviceContactUseCase.getDeviceContacts()
                _uiState.value = UiState.Success(
                    serverContacts = recipients,
                    deviceContacts = deviceContacts
                )
            } catch (e: Exception) {
                Log.e("ChooseRecipientViewModel", "Error fetching customer profile", e)
                _uiState.value = UiState.Error("Failed to fetch customer profile")
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        // Handle search query changes
        // You can filter the list of recipients based on the query here
        val currentState = _uiState.value
        if (currentState is UiState.Success) {
            val filteredContacts = currentState.serverContacts.filter {
                it.displayName.contains(query, ignoreCase = true)
            } + currentState.deviceContacts.filter {
                it.displayName.contains(query, ignoreCase = true)
            }
            _uiState.value = currentState.copy(
                searchQuery = query,
                filteredContacts = filteredContacts
            )
        }
    }
}