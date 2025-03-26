package com.example.chooserecipientcompose.domain.mapper

import com.example.chooserecipientcompose.data.remote.model.*
import com.example.chooserecipientcompose.domain.model.*

fun CustomerProfileDto.toDomainModel(): CustomerProfile {
    return CustomerProfile(
        outageFlag = this.outageFlag ?: "true",
        customer = this.customer?.toDomainModel() ?: Customer(EligibilityProfile("false")),
        recipients = this.recipientDto?.map { it.toDomainModel() } ?: emptyList()
    )
}

fun CustomerDto.toDomainModel(): Customer {
    return Customer(
        eligibilityProfile = this.eligibilityProfile?.toDomainModel() ?: EligibilityProfile("false")
    )
}

fun EligibilityProfileDto.toDomainModel(): EligibilityProfile {
    return EligibilityProfile(payEligibility = this.payEligibility ?: "false")
}

fun RecipientDto.toDomainModel(): Recipient {
    return Recipient(
        recipientId = this.recipientId ?: "",
        displayName = this.displayName ?: "",
        token = this.token ?: "",
        tokenType = this.tokenType ?: "",
        recipientTokenStatus = this.recipientTokenStatus ?: "",
        photoUri = ""
    )
}