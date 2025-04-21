package com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.features.generated.resources.Res
import cmp_notes.composeapp.features.generated.resources.sign_in_button_sign_in
import cmp_notes.composeapp.features.generated.resources.sign_in_input_login
import cmp_notes.composeapp.features.generated.resources.sign_in_input_password
import cmp_notes.composeapp.features.generated.resources.sign_in_title
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.widget.AppToolbar
import com.kursor.chronicles_of_ww2.core.widget.BackButton
import com.kursor.chronicles_of_ww2.core.widget.button.AppButton
import com.kursor.chronicles_of_ww2.core.widget.text_field.AppTextField
import org.jetbrains.compose.resources.stringResource
import ru.mobileup.samples.core.widget.button.ButtonType

@Composable
fun SignInUi(
    component: SignInComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolbar(
                navigationIcon = { BackButton(onBack = component::onBack) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.sign_in_title),
                    style = CustomTheme.typography.title.heading,
                    color = CustomTheme.colors.text.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(28.dp))

                AppTextField(
                    inputControl = component.loginInputControl,
                    placeholder = stringResource(Res.string.sign_in_input_login)
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppTextField(
                    inputControl = component.passwordInputControl,
                    placeholder = stringResource(Res.string.sign_in_input_password)
                )
            }

            AppButton(
                buttonType = ButtonType.Primary,
                onClick = component::onSignUpClick,
                text = stringResource(Res.string.sign_in_button_sign_in),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}