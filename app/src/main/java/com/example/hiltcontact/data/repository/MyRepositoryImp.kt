package com.example.hiltcontact.data.repository

import com.example.hiltcontact.data.remote.MyApi
import com.example.hiltcontact.model.ContactRequest
import com.example.hiltcontact.model.ContactResponse
import com.example.hiltcontact.model.Response
import javax.inject.Inject

class MyRepositoryImp @Inject constructor(private val api: MyApi) : MyRepository {

    override suspend fun getAllContact(): List<ContactResponse> = api.getAllContact().body()!!

    override suspend fun addContact(contactRequest: ContactRequest): Response =
        api.addContact(contactRequest).body()!!

    override suspend fun updateContacts(contactResponse: ContactResponse): Response =
        api.updateContact(contactResponse).body()!!

    override suspend fun deleteContacts(id: Int): Response = api.deleteContact(id).body()!!

}