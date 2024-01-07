package com.app.dw2024.repository.interfaces

import com.app.dw2024.model.User

interface UserRepository {
    fun generateUserId(): Int
    suspend fun saveUserId(userId: Int)
    fun getUserId(): Int
    suspend fun getUserInfo(userId: Int): User?
    suspend fun updateUserPoints(userId: Int, points: Int)
}