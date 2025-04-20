package com.kursor.chronicles_of_ww2

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.commonCoreModule
import com.kursor.chronicles_of_ww2.core.configuration.BuildType
import com.kursor.chronicles_of_ww2.core.configuration.Configuration
import com.kursor.chronicles_of_ww2.core.platformCoreModule
import com.kursor.chronicles_of_ww2.features.allFeatureModules
import com.kursor.chronicles_of_ww2.features.root.createRootComponent
import com.kursor.chronicles_of_ww2.features.root.presentation.RootComponent
import org.koin.core.Koin

class SharedApplication(configuration: Configuration) {

    private val koin: Koin

    init {
        if (configuration.buildType == BuildType.Release) {
            Logger.setMinSeverity(Severity.Assert)
        }
        koin = createKoin(configuration)
    }

    fun createRootComponent(
        componentContext: ComponentContext
    ): RootComponent {
        return koin.get<ComponentFactory>().createRootComponent(componentContext)
    }

    internal inline fun <reified T : Any> get(): T = koin.get<T>()

    private fun createKoin(configuration: Configuration): Koin {
        return Koin().apply {
            loadModules(
                listOf(
                    commonCoreModule(configuration),
                    platformCoreModule(configuration),
                ) + allFeatureModules
            )
            declare(ComponentFactory(this))
            createEagerInstances()
        }
    }
}

interface SharedApplicationProvider {
    val sharedApplication: SharedApplication
}