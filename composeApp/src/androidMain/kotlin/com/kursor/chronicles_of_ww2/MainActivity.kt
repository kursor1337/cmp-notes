package com.kursor.chronicles_of_ww2

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.kursor.chronicles_of_ww2.core.theme.AppTheme
import com.kursor.chronicles_of_ww2.features.root.presentation.RootUi

// Note: rootComponent survives configuration changes due to "android:configChanges" setting in the manifest.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        val activityProvider = application.sharedApplication.activityProvider
        activityProvider.attachActivity(this)
        lifecycle.asEssentyLifecycle().doOnDestroy {
            activityProvider.detachActivity()
        }

        val rootComponent = retainedComponent { componentContext ->
            application.sharedApplication.createRootComponent(componentContext)
        }
        setContent {
            AppTheme {
                RootUi(
                    component = rootComponent,
                    modifier = Modifier.systemBarsPadding()
                )
            }
        }
    }
}