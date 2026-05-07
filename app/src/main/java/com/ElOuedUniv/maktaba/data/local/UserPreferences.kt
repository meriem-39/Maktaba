package com.ElOuedUniv.maktaba.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")
    }

    // حفظ القيمة
    suspend fun saveOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = completed
        }
    }

    // قراءة القيمة
    val isOnboardingCompleted = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_KEY] ?: false
        }
}