package com.example.chooserecipientcompose.data.remote.model

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