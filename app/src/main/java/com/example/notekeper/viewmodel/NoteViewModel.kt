package com.tuapp.notekeper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.tuapp.notekeper.data.AppDatabase
import com.tuapp.notekeper.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = AppDatabase.getDatabase(application).noteDao()

    // LiveData para observar las notas
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    // Cargar todas las notas desde la base de datos
    fun loadNotes() {
        viewModelScope.launch {
            val notesList = noteDao.getAllNotes()
            _notes.postValue(notesList)  // Actualiza el LiveData con las notas
        }
    }

    // Agregar una nueva nota
    fun addNote(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
            loadNotes()  // Recargar las notas después de insertar una nueva
        }
    }
}
