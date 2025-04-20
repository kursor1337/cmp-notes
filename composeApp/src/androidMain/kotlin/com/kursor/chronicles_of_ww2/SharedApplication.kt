package com.kursor.chronicles_of_ww2

import android.app.Application
import com.kursor.chronicles_of_ww2.core.activity.ActivityProvider
import com.kursor.chronicles_of_ww2.core.debug_tools.AndroidDebugTools
import me.aartikov.replica.client.ReplicaClient

val Application.sharedApplication get() = (this as SharedApplicationProvider).sharedApplication

val SharedApplication.activityProvider get() = get<ActivityProvider>()

fun SharedApplication.launchAndroidDebugTools() {
    val replicaClient = get<ReplicaClient>()
    val androidDebugTools = get<AndroidDebugTools>()
    androidDebugTools.launch(replicaClient)
}