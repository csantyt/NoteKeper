package com.tuapp.notekeper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuapp.notekeper.data.AppDatabase
import com.tuapp.notekeper.model.Note
import com.tuapp.notekeper.model.User


@Database(entities = [User::class, Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_keeper_db"
                )
                    .fallbackToDestructiveMigration() // borra y crea si cambia la versión
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
