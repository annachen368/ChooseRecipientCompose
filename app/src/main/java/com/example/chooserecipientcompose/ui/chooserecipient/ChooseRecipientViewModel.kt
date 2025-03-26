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
            val deviceContacts: List<Recipient>
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
}