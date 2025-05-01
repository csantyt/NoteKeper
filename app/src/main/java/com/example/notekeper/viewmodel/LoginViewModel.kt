package com.tuapp.notekeeper.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun validateCredentials(username: String, password: String): Boolean {
        return username.isNotBlank() && password.matches(Regex("\\d{4}"))
    }
}
