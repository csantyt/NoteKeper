//package com.example.notekeper.data
package com.tuapp.notekeper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuapp.notekeper.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

}




