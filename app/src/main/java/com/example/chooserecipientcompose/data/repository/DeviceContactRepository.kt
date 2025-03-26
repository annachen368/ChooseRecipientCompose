package com.example.chooserecipientcompose.data.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.chooserecipientcompose.data.local.model.DeviceContactDataModel
import javax.inject.Inject

class DeviceContactRepository @Inject constructor(private val contentResolver: ContentResolver) {

    suspend fun fetchDeviceContacts(): List<DeviceContactDataModel> {
        val contactMap = mutableMapOf<String, DeviceContactDataModel>()

        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
        )

        val selection = "${ContactsContract.Data.MIMETYPE} IN (?, ?, ?)"
        val selectionArgs = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
        )

        val cursor = contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            val contactIdIdx = it.getColumnIndex(ContactsContract.Data.CONTACT_ID)
            val mimeTypeIdx = it.getColumnIndex(ContactsContract.Data.MIMETYPE)
            val nameIdx =
                it.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)
            val phoneIdx = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val emailIdx = it.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
            val photoUriIdx = it.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)
            var tokenType = "Unknown"

            while (it.moveToNext()) {
                val contactId = it.getString(contactIdIdx)
                val mimeType = it.getString(mimeTypeIdx)

                val existingContact = contactMap.getOrPut(contactId) {
                    DeviceContactDataModel(name = null, phone = null, email = null, photoUri = null)
                }

                when (mimeType) {
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE -> {
                        existingContact.name = it.getString(nameIdx)
                    }

                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE -> {
                        if (existingContact.phone == null) { // Get only one phone
                            existingContact.phone = it.getString(phoneIdx)
                        }
                    }

                    ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE -> {
                        if (existingContact.email == null) { // Get only one email
                            existingContact.email = it.getString(emailIdx)
                        }
                    }
                }

                if (existingContact.photoUri == null) {
                    existingContact.photoUri = it.getString(photoUriIdx)
                }
            }
        }

        return contactMap.values.toList()
    }
}