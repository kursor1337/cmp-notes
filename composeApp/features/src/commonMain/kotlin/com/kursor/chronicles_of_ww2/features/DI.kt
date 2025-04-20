package com.kursor.chronicles_of_ww2.features

import com.kursor.chronicles_of_ww2.features.auth.authModule
import com.kursor.chronicles_of_ww2.features.notes.notesModule

val allFeatureModules = listOf(
    authModule,
    notesModule
)