package com.tuapp.notekeper.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tuapp.notekeper.databinding.ActivityLoginBinding
import com.tuapp.notekeeper.viewmodel.LoginViewModel



class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (loginViewModel.validateCredentials(user, pass)) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                // Aquí iría la navegación a la siguiente pantalla
            } else {
                Toast.makeText(this, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
