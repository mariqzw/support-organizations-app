package com.mariqzw.supportorganizationsapp.auth.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.AuthorizationViewModel
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.LoginScreenState
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.PasswordTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.provider.LoсalSnackbarHostState
import com.mariqzw.supportorganizationsapp.ui.provider.showMessage
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto24
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    navController: NavController,
    onRegisterClick: () -> Unit,
    viewModel: AuthorizationViewModel = koinViewModel()
) {
    val dimensions = LocalDimensions.current
    val snackbarHostState = LoсalSnackbarHostState.current

    val screenState by viewModel.state.collectAsState(initial = LoginScreenState.Init)
    val authData by viewModel.authDataState.collectAsState()

    // Навигация или показ ошибки в зависимости от состояния экрана
    LaunchedEffect(screenState) {
        when (screenState) {
            is LoginScreenState.Success -> {
                navController.navigate(Route.MapScreen) {
                    popUpTo(Route.AuthorizationScreen) { inclusive = true }
                }
            }
            is LoginScreenState.Error -> {
                (screenState as LoginScreenState.Error).message?.also { message ->
                    snackbarHostState.showMessage(message = message)
                }
            }

            else -> {
                /* Do nothing */
            }
        }
    }

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
                value = authData.email,
                onValueChange = { newEmail ->
                    viewModel.changeEmail(newEmail)
                },
                labelText = "Электронная почта",
                isError = false,
                errorText = "Введено некорректное значение"
            )

            PasswordTextField(
                value = authData.password,
                labelText = "Пароль",
                onTextChange = { newPassword ->
                    viewModel.changePassword(newPassword)
                },
                isError = false,
                errorText = "Пароль должен состоять не менее чем из 8 символов"
            )

            PrimaryButton(
                text = "Войти",
                isLoading = false,
                onButtonClick = { viewModel.signIn() }
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

//@Composable
//@Preview
//fun AuthorizationScreenPreview() {
//    AuthorizationScreen(
//        onRegisterClick = {
//            // Do nothing
//        }
//    )
//}
