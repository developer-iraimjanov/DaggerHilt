package com.example.hiltcontact.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hiltcontact.data.repository.MyRepository
import com.example.hiltcontact.model.ContactRequest
import com.example.hiltcontact.model.ContactResponse
import com.example.hiltcontact.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {

    fun getAllContact(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = true
        viewModelScope.launch {
            getResponse.value = repository.getAllContact()
        }
    }

    fun addContact(
        contactRequest: ContactRequest,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        swipeRefreshLayout.isRefreshing = true
        viewModelScope.launch {
            audResponse.value = repository.addContact(contactRequest)
        }
    }

    fun updateContact(
        contactResponse: ContactResponse,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        swipeRefreshLayout.isRefreshing = true
        viewModelScope.launch {
            audResponse.value = repository.updateContacts(contactResponse)
        }
    }

    fun deleteContact(id: Int, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = true
        viewModelScope.launch {
            audResponse.value = repository.deleteContacts(id)
        }
    }

    var getResponse = MutableLiveData<List<ContactResponse>>()
    var audResponse = MutableLiveData<Response>()
}