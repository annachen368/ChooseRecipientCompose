package com.example.chooserecipientcompose.domain.mapper

import com.example.chooserecipientcompose.data.local.model.DeviceContactDataModel
import com.example.chooserecipientcompose.domain.model.Recipient

fun DeviceContactDataModel.toDomainModel(): Recipient {
    return Recipient(
        recipientId = "",
        displayName = this.name ?: "",
        token = this.phone ?: this.email ?: "",
        tokenType = getTokenType(this.phone ?: this.email ?: ""),
        recipientTokenStatus = "",
        photoUri = this.photoUri ?: ""
    )
}

fun getTokenType(token: String): String {
    return if (token.contains("@")) {
        "Email"
    } else {
        "Phone"
    }
}