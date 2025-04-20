package com.kursor.chronicles_of_ww2

import android.content.Context
import com.kursor.chronicles_of_ww2.core.debug_tools.AndroidDebugTools
import me.aartikov.replica.client.ReplicaClient
import okhttp3.Interceptor

class RealAndroidDebugTools(private val context: Context) : AndroidDebugTools {

    override val interceptors: List<Interceptor> = emptyList()

    override fun launch(replicaClient: ReplicaClient) {
        // do nothing
    }
}