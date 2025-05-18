package com.mariqzw.supportorganizationsapp.data.auth

import com.mariqzw.supportorganizationsapp.model.response.AuthDataStore
import kotlinx.coroutines.flow.Flow

interface AuthenticationDataStore {

    /**
     * Метод, который возвращает поток данных о пользователе.
     */
    suspend fun fetchUsers(): Flow<AuthDataStore>

    suspend fun setUserId(userId: Long)

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    /**
     * Метод, который устанавливает время жизни токена доступа.
     */
    suspend fun setAuthenticated(isAuthenticated: Boolean)

    suspend fun updateUser(user: AuthDataStore)

    suspend fun clear()
}
