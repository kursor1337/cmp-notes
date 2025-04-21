package com.kursor.chronicles_of_ww2.core.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSUserDefaults
import platform.Security.kSecAttrAccessible
import platform.Security.kSecAttrAccessibleAfterFirstUnlock

@OptIn(ExperimentalSettingsApi::class)
class IosSettingsFactory : SettingsFactory {

    override fun createSettings(): SuspendSettings {
        return NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
            .toSuspendSettings(Dispatchers.IO)
    }

    @OptIn(ExperimentalSettingsImplementation::class, ExperimentalForeignApi::class)
    override fun createEncryptedSettings(): SuspendSettings {
        return KeychainSettings(
            kSecAttrAccessible to kSecAttrAccessibleAfterFirstUnlock
        )
            .toSuspendSettings(Dispatchers.IO)
    }
}