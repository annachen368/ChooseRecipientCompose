package com.example.chooserecipientcompose.data.remote.model

import com.example.chooserecipientcompose.domain.model.Customer
import com.example.chooserecipientcompose.domain.model.CustomerProfile
import com.example.chooserecipientcompose.domain.model.EligibilityProfile
import com.example.chooserecipientcompose.domain.model.Recipient
import com.google.gson.annotations.SerializedName

/**
 * A DTO is a simple data container used to transfer data between layers, components, or over the
 * network. It's typically a plain Kotlin data class (or Java class) that does not contain any
 * business logic, just fields and sometimes annotations.
 */
data class CustomerProfileDTO(
    val outageFlag: String?,
    val customer: CustomerDto?,
    @SerializedName("recipients") val recipientDto: List<RecipientDto>?
)

data class CustomerDto(
    val eligibilityProfile: EligibilityProfileDto?,
    val recipients: List<RecipientDto>?
)

data class EligibilityProfileDto(val payEligibility: String?)

data class RecipientDto(
    val recipientId: String?,
    val displayName: String?,
    val token: String?,
    val tokenType: String?,
    val recipientTokenStatus: String?
)

fun CustomerProfileDTO.toDomainModel(): CustomerProfile {
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
        recipientTokenStatus = this.recipientTokenStatus ?: ""
    )
}