package com.tuapp.notekeper

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.tuapp.notekeper.databinding.ActivityMainBinding
import com.tuapp.notekeper.ui.notes.NotesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Puedes mostrar un mensaje o una vista de bienvenida
        binding.textView.text = "Bienvenido a NoteKeper"

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, NotesActivity::class.java))
            finish()
        }, 2000)
    }
}
