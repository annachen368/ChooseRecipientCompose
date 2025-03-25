package com.example.chooserecipientcompose.domain.usecase

import com.example.chooserecipientcompose.data.repository.CustomerProfileRepository
import com.example.chooserecipientcompose.domain.mapper.toDomainModel
import com.example.chooserecipientcompose.domain.model.CustomerProfile
import javax.inject.Inject

class GetCustomerProfileUseCase @Inject constructor(private val repository: CustomerProfileRepository) {
    suspend fun getCustomerProfile(): CustomerProfile {
        return repository.fetchCustomerProfileDto().toDomainModel()
    }
}