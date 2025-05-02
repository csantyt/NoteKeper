package com.tuapp.notekeper.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tuapp.notekeper.databinding.ActivityLoginBinding
import com.tuapp.notekeper.viewmodel.LoginViewModel
import com.tuapp.notekeper.MainActivity
import com.tuapp.notekeper.data.AppDatabase

import com.tuapp.notekeper.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.tuapp.notekeper.ui.notes.NotesActivity




class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val userDao = AppDatabase.getDatabase(applicationContext).userDao()
            if (userDao.getAllUsers().isEmpty()) {
                userDao.insertUser(User(username = "jara", password = "1234"))
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()


            loginViewModel.validateCredentials(username, password) { isValid ->
                if (isValid) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }
}
