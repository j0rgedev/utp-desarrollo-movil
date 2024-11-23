package com.example.pc2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc2.data.local.dao.ClientDao
import com.example.pc2.data.local.entity.Client
import kotlinx.coroutines.launch
import kotlin.compareTo

class ClientViewModel(
    private val clientDao: ClientDao
): ViewModel() {
    var clients by mutableStateOf(listOf<Client>())
        private set

    var client by mutableStateOf(Client())

    init {
        viewModelScope.launch {
            clientDao.findAll().collect {
                clients = it
            }
        }
    }

    fun add() = viewModelScope.launch {
        clientDao.add(client = client)
    }

    fun searchClient(lastNames: String) = viewModelScope.launch {
        clients = clientDao.searchClient(lastNames = lastNames)
    }

    fun existsClient(dni: String, callback: (Boolean) -> Unit) = viewModelScope.launch {
        val exists = clientDao.existsClient(dni = dni) > 0
        callback(exists)
    }

    fun updateClientDni(dni: String) {
        client = client.copy(dni = dni)
    }

    fun updateClientNames(names: String) {
        client = client.copy(names = names)
    }

    fun updateClientLastNames(lastNames: String) {
        client = client.copy(lastNames = lastNames)
    }

    fun updateClientPhoneOperator(phoneOperator: String) {
        client = client.copy(phoneOperator = phoneOperator)
    }

    fun updateClientPhoneNumber(phoneNumber: String) {
        client = client.copy(phoneNumber = phoneNumber)
    }

    fun updatePhoto(photo: Int) {
        client = client.copy(photo = photo)
    }
}