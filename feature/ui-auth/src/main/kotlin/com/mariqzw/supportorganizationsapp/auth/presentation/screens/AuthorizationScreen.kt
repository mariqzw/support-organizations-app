package com.mariqzw.supportorganizationsapp.auth.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.PasswordTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto24
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import timber.log.Timber

@Composable
fun AuthorizationScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isError = password.length < 8 && password.isNotEmpty()


    val dimensions = LocalDimensions.current


    Surface(
        color = backgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensions.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(dimensions.verticalSmall, Alignment.CenterVertically)
        ) {
            Text(
                text = "Вход",
                style = RegularRoboto24,
                fontWeight = FontWeight.Bold
            )

            PrimaryTextField(
                value = login,
                onValueChange = {
                    login = it
                    Timber.d("textForField", login)
                },
                labelText = "Телефон/электронная почта",
                isError = false,
                errorText = "Введено некорректное значение"
            )

            PasswordTextField(
                value = password,
                labelText = "Пароль",
                onTextChange = {
                    password = it
                },
                isError = isError,
                errorText = "Пароль должен состоять не менее чем из 8 символов"
            )

            PrimaryButton(
                text = "Войти",
                isLoading = false,
                onButtonClick = onLoginClick
            )

            Text(text = "Нет аккаунта?", style = RegularRoboto14)

            Text(
                text = "Зарегистрироваться",
                style = RegularRoboto14,
                modifier = Modifier.clickable(onClick = onRegisterClick)
            )

        }
    }
}

@Composable
@Preview
fun AuthorizationScreenPreview() {
    AuthorizationScreen(
        onLoginClick = {
            // Do nothing
        },
        onRegisterClick = {
            // Do nothing
        }
    )
}
