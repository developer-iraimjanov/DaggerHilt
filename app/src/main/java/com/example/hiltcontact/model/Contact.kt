package com.example.hiltcontact.model


data class ContactResponse(
    var id: Int = 0,
    var name: String = "",
    var surname: String = "",
    var number: String = "",
)

data class ContactRequest(
    var name: String = "",
    var surname: String = "",
    var number: String = "",
)