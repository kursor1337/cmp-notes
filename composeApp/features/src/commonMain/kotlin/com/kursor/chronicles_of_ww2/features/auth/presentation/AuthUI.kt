package com.kursor.chronicles_of_ww2.features.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.kursor.chronicles_of_ww2.core.utils.predictiveBackAnimation
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in.SignInUi
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up.SignUpUi
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.WelcomeUi

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun AuthUi(
    component: AuthComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(
        stack = childStack,
        modifier = modifier,
        animation = component.predictiveBackAnimation()
    ) { child: Child.Created<*, AuthComponent.Child> ->
        when (val instance = child.instance) {
            is AuthComponent.Child.SignIn -> SignInUi(instance.component)
            is AuthComponent.Child.SignUp -> SignUpUi(instance.component)
            is AuthComponent.Child.Welcome -> WelcomeUi(instance.component)
        }
    }
}