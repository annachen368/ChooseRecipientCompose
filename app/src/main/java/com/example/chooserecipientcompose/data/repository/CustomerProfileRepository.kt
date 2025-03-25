package com.example.chooserecipientcompose.data.repository

import com.example.chooserecipientcompose.data.api.ApiService
import com.example.chooserecipientcompose.data.remote.model.CustomerProfileDto
import javax.inject.Inject

class CustomerProfileRepository @Inject constructor(private val apiService: ApiService) {

    private var cachedCustomerProfileDto: CustomerProfileDto? = null

    suspend fun fetchCustomerProfileDto(): CustomerProfileDto {
        return cachedCustomerProfileDto ?: apiService.getCustomerProfileDto().also {
            cachedCustomerProfileDto = it
        }
    }

    fun getCachedCustomerProfileDto(): CustomerProfileDto? {
        return cachedCustomerProfileDto
    }
}