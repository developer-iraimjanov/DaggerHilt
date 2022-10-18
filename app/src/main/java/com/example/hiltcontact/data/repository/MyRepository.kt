package com.example.hiltcontact.data.repository

import com.example.hiltcontact.model.ContactRequest
import com.example.hiltcontact.model.ContactResponse
import com.example.hiltcontact.model.Response

interface MyRepository {
    suspend fun getAllContact(): List<ContactResponse>
    suspend fun addContact(contactRequest: ContactRequest): Response
    suspend fun updateContacts(contactResponse: ContactResponse): Response
    suspend fun deleteContacts(id: Int): Response
}