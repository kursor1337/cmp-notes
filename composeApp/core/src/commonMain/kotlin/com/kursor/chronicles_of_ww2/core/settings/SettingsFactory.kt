package com.kursor.chronicles_of_ww2.core.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings

@OptIn(ExperimentalSettingsApi::class)
interface SettingsFactory {

    fun createSettings(name: String): SuspendSettings

    fun createEncryptedSettings(): SuspendSettings
}