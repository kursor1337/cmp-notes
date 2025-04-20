package com.kursor.chronicles_of_ww2.core.configuration

import android.app.Application
import com.kursor.chronicles_of_ww2.core.debug_tools.AndroidDebugTools

actual class Platform(
    val application: Application,
    val debugTools: AndroidDebugTools
) {
    actual val type = PlatformType.Android
}