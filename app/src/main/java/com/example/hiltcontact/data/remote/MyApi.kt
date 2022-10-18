package com.example.hiltcontact.data.remote

import com.example.hiltcontact.model.ContactRequest
import com.example.hiltcontact.model.ContactResponse
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("get")
    suspend fun getAllContact(): Response<List<ContactResponse>>

    @POST("add")
    suspend fun addContact(
        @Body contactRequest: ContactRequest
    ): Response<com.example.hiltcontact.model.Response>

    @PUT("update")
    suspend fun updateContact(
        @Body contactResponse: ContactResponse
    ): Response<com.example.hiltcontact.model.Response>

    @FormUrlEncoded
    @POST("delete")
    suspend fun deleteContact(
        @Field("id") id: Int
    ): Response<com.example.hiltcontact.model.Response>

}