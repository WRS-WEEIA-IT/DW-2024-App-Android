package com.app.dw2024.repository.impl

import android.content.SharedPreferences
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
    override fun generateUserId(): Int = System.currentTimeMillis().hashCode()

    override suspend fun saveUserId(userId: Int) {
        sharedPreferences.edit().putInt("user_id", userId).apply()
        db.collection("users").document(userId.toString()).set(
            User(
                id = userId,
                points = 0,
                time = Timestamp.now(),
                winner = false
            )
        ).await()
    }

    override fun getUserId(): Int = sharedPreferences.getInt("user_id", 0)

    override suspend fun getUserInfo(userId: Int): User? {
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

    override suspend fun updateUserPoints(userId: Int, points: Int) {
        db.collection("users")
            .document(userId.toString())
            .update("points", points)
            .await()
    }
}