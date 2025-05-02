package com.tuapp.notekeper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuapp.notekeper.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Int): Note?
}
