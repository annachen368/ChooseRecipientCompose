package com.example.chooserecipientcompose.domain.model

data class CustomerProfile(
    val outageFlag: String,
    val customer: Customer,
    val recipients: List<Recipient>
)

data class Customer(val eligibilityProfile: EligibilityProfile)

data class EligibilityProfile(val payEligibility: String)

data class Recipient(
    val recipientId: String,
    val displayName: String,
    val token: String,
    val tokenType: String,
    val recipientTokenStatus: String,
    val photoUri: String
)