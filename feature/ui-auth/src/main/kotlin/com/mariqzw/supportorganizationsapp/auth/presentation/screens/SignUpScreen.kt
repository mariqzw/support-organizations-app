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
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SignUpScreenState
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SignUpViewModel
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.PasswordTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto24
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    navController: NavController,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val dimensions = LocalDimensions.current

    val uiState by viewModel.state.collectAsState(initial = SignUpScreenState.Init)

    val phone by viewModel.number.collectAsState()
    val email by viewModel.email.collectAsState()
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val password by viewModel.password.collectAsState()

    val numberError by viewModel.numberErr.collectAsState()
    val emailError by viewModel.emailErr.collectAsState()
    val firstNameError by viewModel.firstNameErr.collectAsState()
    val lastNameError by viewModel.lastNameErr.collectAsState()
    val passwordError by viewModel.passwordErr.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState) {
            is SignUpScreenState.Success -> {
                navController.navigate(Route.MapScreen)
            }

            else -> {}
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
                text = "Регистрация",
                style = RegularRoboto24,
                fontWeight = FontWeight.Bold
            )

            PrimaryTextField(
                value = phone,
                onValueChange = viewModel::onNumberChanged,
                labelText = "Телефон",
                isError = numberError != null,
                keyboardType = KeyboardType.Phone,
                errorText = "Введено некорректное значение"
            )

            PrimaryTextField(
                value = email,
                onValueChange = viewModel::onEmailChanged,
                labelText = "Электронная почта",
                isError = emailError != null,
                keyboardType = KeyboardType.Email,
                errorText = "Введено некорректное значение"
            )

            PrimaryTextField(
                value = firstName,
                onValueChange = viewModel::onFirstNameChanged,
                labelText = "Имя",
                isError = firstNameError != null,
                errorText = "Введено некорректное значение"
            )

            PrimaryTextField(
                value = lastName,
                onValueChange = viewModel::onLastNameChanged,
                labelText = "Фамилия",
                isError = lastNameError != null,
                errorText = "Введено некорректное значение"
            )

            PasswordTextField(
                value = password,
                labelText = "Пароль",
                onTextChange = viewModel::onPasswordChanged,
                isError = passwordError != null,
                errorText = "Пароль должен состоять не менее чем из 8 символов"
            )

            PrimaryButton(
                text = "Зарегистрироваться",
                isLoading = false,
                onButtonClick = viewModel::submit
            )

            Text(
                text = "Вход",
                style = RegularRoboto14,
                modifier = Modifier.clickable(onClick = onLoginClick)
            )

        }
    }
}

//@Composable
//@Preview
//fun RegistrationScreenPreview() {
//    RegistrationScreen(
//        onLoginClick = {
//            // Do nothing
//        },
//        onRegisterClick = {
//            // Do nothing
//        }
//    )
//}
