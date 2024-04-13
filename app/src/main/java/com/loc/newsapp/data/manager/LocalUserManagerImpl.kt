package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.Constants
import com.loc.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context : Context
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun saveIsBookmarked(articleUrl: String, isBookmarked: Boolean) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.isBookmarked(articleUrl)] = isBookmarked
        }
    }

    override fun readIsBookmarked(articleUrl: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.isBookmarked(articleUrl)] ?: false
        }
    }
}

//to read key value from dataStore
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)


//to save key values in dataStore
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    fun isBookmarked(articleUrl: String) = booleanPreferencesKey(name = "is_bookmarked_$articleUrl")
}

