package com.example.chooserecipientcompose.ui.welcome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chooserecipientcompose.domain.usecase.GetCustomerProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val getCustomerProfileUseCase: GetCustomerProfileUseCase) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val message: String) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = mutableStateOf<UiState>(UiState.Loading)
    val uiState = _uiState

    init {
        getWelcome()
    }

    private fun getWelcome() {
        viewModelScope.launch {
            val customerProfile = getCustomerProfileUseCase.getCustomerProfile()
            val message = if (customerProfile.customer.eligibilityProfile.payEligibility == "Y") {
                "Welcome!"
            } else {
                "Sorry, you are not eligible to pay"
            }
            _uiState.value = UiState.Success(message)
        }
    }
}