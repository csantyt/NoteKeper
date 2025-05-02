package com.tuapp.notekeper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuapp.notekeper.data.AppDatabase
import com.tuapp.notekeper.model.User
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    fun validateCredentials(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user: User? = userDao.getUserByUsername(username)
            if (user != null && user.password == password) {
                onResult(true)  // Credenciales correctas
            } else {
                onResult(false)  // Credenciales incorrectas
            }
        }
    }
}
