package com.nst.dhanam.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

class PreferencesManager (
     private val context: Context
) {
    private val tokenKey = stringPreferencesKey(TOKEN_KEY)

    companion object {
        private const val TOKEN_KEY = "token_key"
    }


    private fun getPreferences(): Flow<Preferences> {
        return context.dataStore.data
    }

    private fun editPreferences(): DataStore<Preferences> {
        return context.dataStore
    }

    val token: Flow<String?> = getPreferences().map { preferences ->
        preferences[tokenKey]
    }

    suspend fun setToken(token: String) {
        editPreferences().edit { preferences ->
            preferences[tokenKey] = token
        }
    }


}