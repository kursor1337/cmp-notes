package com.kursor.chronicles_of_ww2.core.configuration

enum class PlatformType {
    Android,
    Ios,
    Desktop,
    Web
}

expect class Platform {
    val type: PlatformType
}