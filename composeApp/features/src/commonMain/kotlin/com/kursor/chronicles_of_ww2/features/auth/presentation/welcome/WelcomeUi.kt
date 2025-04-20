package com.kursor.chronicles_of_ww2.features.auth.presentation.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.features.generated.resources.Res
import cmp_notes.composeapp.features.generated.resources.welcome_button_sign_in
import cmp_notes.composeapp.features.generated.resources.welcome_button_sign_up
import cmp_notes.composeapp.features.generated.resources.welcome_title
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.widget.button.AppButton
import org.jetbrains.compose.resources.stringResource
import ru.mobileup.samples.core.widget.button.ButtonType

@Composable
fun WelcomeUi(
    component: WelcomeComponent,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(20.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(Res.string.welcome_title),
                style = CustomTheme.typography.title.heading,
                color = CustomTheme.colors.text.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }

        AppButton(
            buttonType = ButtonType.Primary,
            onClick = component::onSignInClick,
            text = stringResource(Res.string.welcome_button_sign_in),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            buttonType = ButtonType.Secondary,
            onClick = component::onSignUpClick,
            text = stringResource(Res.string.welcome_button_sign_up),
            modifier = Modifier.fillMaxWidth()
        )
    }
}