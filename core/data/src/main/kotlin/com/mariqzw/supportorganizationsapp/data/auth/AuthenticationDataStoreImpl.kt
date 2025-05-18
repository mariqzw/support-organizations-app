package com.mariqzw.supportorganizationsapp.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mariqzw.supportorganizationsapp.model.response.AuthDataStore
import kotlinx.coroutines.flow.map

class AuthenticationDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : AuthenticationDataStore {

    override suspend fun fetchUsers() = dataStore.data.map { prefs ->
        AuthDataStore(
            userId = prefs[USER_ID],
            accessToken = prefs[ACCESS_TOKEN],
            refreshToken = prefs[REFRESH_TOKEN],
            isAuthenticated = prefs[IS_AUTHENTICATED]
        )
    }

    override suspend fun setUserId(userId: Long) {
        dataStore.edit { it[USER_ID] = userId }
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { it[ACCESS_TOKEN] = accessToken }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { it[REFRESH_TOKEN] = refreshToken }
    }

    override suspend fun setAuthenticated(isAuthenticated: Boolean) {
        dataStore.edit { it[IS_AUTHENTICATED] = isAuthenticated }
    }

    override suspend fun updateUser(user: AuthDataStore) {
        dataStore.edit { preferences ->
            with(user) {
                userId?.let { id ->
                    preferences[USER_ID] = id
                } ?: preferences.remove(USER_ID)

                accessToken?.let { accessToken ->
                    preferences[ACCESS_TOKEN] = accessToken
                } ?: preferences.remove(ACCESS_TOKEN)

                refreshToken?.let { refreshToken ->
                    preferences[REFRESH_TOKEN] = refreshToken
                } ?: preferences.remove(REFRESH_TOKEN)

                isAuthenticated?.let { isAuthenticated ->
                    preferences[IS_AUTHENTICATED] = isAuthenticated
                } ?: preferences.remove(IS_AUTHENTICATED)
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear() // Очищаем все данные
        }
    }

    companion object {
        val USER_ID = longPreferencesKey("userId")
        val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        val REFRESH_TOKEN = stringPreferencesKey("refreshToken")
        val IS_AUTHENTICATED = booleanPreferencesKey("isAuthenticated")
    }
}