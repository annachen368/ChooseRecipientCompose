package com.example.chooserecipientcompose.domain.usecase

import com.example.chooserecipientcompose.data.repository.DeviceContactRepository
import com.example.chooserecipientcompose.domain.mapper.toDomainModel
import javax.inject.Inject

class GetDeviceContactUseCase @Inject constructor(private val deviceContactRepository: DeviceContactRepository) {
    suspend fun getDeviceContacts() = deviceContactRepository.fetchDeviceContacts().map {
        it.toDomainModel()
    }
}