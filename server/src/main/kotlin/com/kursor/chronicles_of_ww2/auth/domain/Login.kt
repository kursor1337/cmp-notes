package com.kursor.chronicles_of_ww2.auth.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class Login(val value: String)