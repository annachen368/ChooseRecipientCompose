package com.example.chooserecipientcompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chooserecipientcompose.domain.usecase.GetWelcomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val getWelcomeUseCase: GetWelcomeUseCase) : ViewModel() {

    private var _title = mutableStateOf("")
    val title = _title

    init {
        getWelcome()
    }

    private fun getWelcome() {
        viewModelScope.launch {
            val customerProfile = getWelcomeUseCase.getCustomerProfile()
            if (customerProfile.customer.eligibilityProfile.payEligibility == "Y") {
                _title.value = "Welcome!"
            } else {
                _title.value = "Sorry, you are not eligible to pay"
            }
        }
    }
}