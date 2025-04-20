package com.kursor.chronicles_of_ww2.core

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * Used to create Decompose components. Creation of components are implemented as extension functions.
 */
class ComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}