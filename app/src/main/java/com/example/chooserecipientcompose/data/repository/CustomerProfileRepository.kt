package com.example.chooserecipientcompose.data.repository

import com.example.chooserecipientcompose.data.api.ApiService
import com.example.chooserecipientcompose.data.remote.model.CustomerProfileDTO
import javax.inject.Inject

class CustomerProfileRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchCustomerProfile(): CustomerProfileDTO {
        return apiService.getCustomerProfile()
    }
}