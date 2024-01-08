package com.app.dw2024.repository.impl

import android.content.SharedPreferences
import android.util.Log
import com.app.dw2024.model.User
import com.app.dw2024.repository.interfaces.UserRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val db: FirebaseFirestore
): UserRepository {
    override suspend fun createNewUser() {
        val userId = System.currentTimeMillis().hashCode()
        sharedPreferences.edit().putInt("user_id", userId).apply()
        db.collection("users")
            .document(userId.toString())
            .set(
                User(
                    id = userId,
                    points = 0,
                    time = Timestamp.now(),
                    winner = false
                )
            )
            .await()
    }

    override fun getUserId(): Int = sharedPreferences.getInt("user_id", 0)

    override suspend fun getUserInfo(): User? {
        val userId = getUserId()
        if (userId == 0) {
            return null
        }
        var user: User? = null
        db.collection("users")
            .document(userId.toString())
            .get()
            .addOnSuccessListener {
                it.toObject(User::class.java)?.let {
                    user = it
                }
            }
            .addOnFailureListener {
                user = null
            }
            .await()
        return user
    }

    override suspend fun incrementUserPoints(points: Int) {
        val currentPoints = getUserInfo()?.points ?: 0
        db.collection("users")
            .document(getUserId().toString())
            .update("points", currentPoints + points)
            .await()
        db.collection("users")
            .document(getUserId().toString())
            .update("time", Timestamp.now())
            .await()
    }
}