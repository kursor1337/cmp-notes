package com.kursor.chronicles_of_ww2.core.settings

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings

class AndroidSettingsFactory(
    private val context: Context
) : SettingsFactory {

    @OptIn(ExperimentalSettingsApi::class)
    override fun createSettings(name: String): SuspendSettings {
        return SharedPreferencesSettings(
            delegate = context.getSharedPreferences(name, Context.MODE_PRIVATE),
            commit = true
        ).toSuspendSettings()
    }

    @OptIn(ExperimentalSettingsApi::class)
    override fun createEncryptedSettings(): SuspendSettings {
        return SharedPreferencesSettings(
            delegate = createEncryptedSharedPreferences(context),
            commit = true
        ).toSuspendSettings()
    }
}

private fun createEncryptedSharedPreferences(
    context: Context
) = EncryptedSharedPreferences.create(
    context,
    "encrypted_prefs",
    MasterKey
        .Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build(),
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)