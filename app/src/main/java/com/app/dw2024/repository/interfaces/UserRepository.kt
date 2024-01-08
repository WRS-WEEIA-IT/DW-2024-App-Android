package com.app.dw2024.repository.interfaces

import com.app.dw2024.model.User

interface UserRepository {
    suspend fun createNewUser()
    fun getUserId(): Int
    suspend fun getUserInfo(): User?
    suspend fun updateUserPoints(userId: Int, points: Int)
}