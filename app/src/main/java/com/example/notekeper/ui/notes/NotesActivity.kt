package com.tuapp.notekeper.ui.notes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuapp.notekeper.databinding.ActivityNotesBinding
import com.tuapp.notekeper.model.Note
import com.tuapp.notekeper.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tuapp.notekeper.data.AppDatabase

import com.tuapp.notekeper.ui.notes.NoteAdapter


class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el RecyclerView
        noteAdapter = NoteAdapter()
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = noteAdapter

        // Configura el ViewModel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Observa las notas de la base de datos
        noteViewModel.notes.observe(this) { notes ->

            noteAdapter.submitList(notes)
        }


        // Guarda una nueva nota
        binding.buttonSaveNote.setOnClickListener {
            val noteText = binding.editTextNote.text.toString()
            if (noteText.isNotBlank()) {
                val note = Note(content = noteText)
                noteViewModel.addNote(note)
                binding.editTextNote.text.clear()
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, escribe una nota", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveNoteToDatabase(noteText: String) {
        val note = Note(content = noteText)

        // Usamos un Coroutine para hacer operaciones de base de datos
        CoroutineScope(Dispatchers.IO).launch {
            val noteDao = AppDatabase.getDatabase(applicationContext).noteDao()
            noteDao.insert(note)

            // Después de insertar, actualiza la UI en el hilo principal
            runOnUiThread {
                Toast.makeText(this@NotesActivity, "Nota guardada", Toast.LENGTH_SHORT).show()
                binding.editTextNote.text.clear()
            }
        }
    }
}
