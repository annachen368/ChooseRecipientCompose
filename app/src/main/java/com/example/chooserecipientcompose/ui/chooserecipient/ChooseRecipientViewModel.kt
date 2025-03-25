package com.example.chooserecipientcompose.ui.chooserecipient

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chooserecipientcompose.domain.model.CustomerProfile
import com.example.chooserecipientcompose.domain.usecase.GetCustomerProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseRecipientViewModel @Inject constructor(private val getCustomerProfileUseCase: GetCustomerProfileUseCase) :
    ViewModel() {
    sealed class UiState {
        object Loading : UiState()
        data class Success(val customerProfile: CustomerProfile) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = mutableStateOf<UiState>(UiState.Loading)
    val uiState = _uiState

    init {
        getCustomerProfile()
    }

    private fun getCustomerProfile() {
        viewModelScope.launch {
            try {
                val customerProfile = getCustomerProfileUseCase.getCustomerProfile()
                _uiState.value = UiState.Success(customerProfile)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch customer profile")
            }
        }
    }
}