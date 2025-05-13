package com.mariqzw.supportorganizationsapp.profile.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.profile.R
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.PasswordTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import timber.log.Timber

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit
) {
    val dimensions = LocalDimensions.current

    val name = remember { mutableStateOf("Иван") }
    val surname = remember { mutableStateOf("Иванов") }
    val phoneNumber = remember { mutableStateOf("+79001002020") }
    val email = remember { mutableStateOf("user@example.com") }
    val password = remember { mutableStateOf("Password11") }

    val isError = password.value.length < 6 && password.value.isNotEmpty()

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
            Image(
                painter = painterResource(id = R.drawable.pc_profile_photo),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(dimensions.sizeOfProfilePhoto)
                    .clip(CircleShape)
                    .padding(bottom = dimensions.verticalMedium),
                contentScale = ContentScale.Fit
            )

            PrimaryTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                    Timber.d("textForField", name)
                },
                labelText = "Имя",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = surname.value,
                onValueChange = {
                    surname.value = it
                    Timber.d("textForField", surname)
                },
                labelText = "Фамилия",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = phoneNumber.value,
                onValueChange = {
                    phoneNumber.value = it
                    Timber.d("textForField", phoneNumber)
                },
                labelText = "Номер телефона",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    Timber.d("textForField", email)
                },
                labelText = "Электронная почта",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PasswordTextField(
                value = password.value,
                onTextChange = {
                    password.value = it
                    Timber.d("textForField", password)
                },
                labelText = "Пароль",
                errorText = "Пароль должен содержать минимум 8 символов",
                isError = isError
            )

            PrimaryButton(
                text = "Сохранить",
                onButtonClick = {
                    // Do nothing
                }
            )

            Text(
                text = "Настройки",
                style = RegularRoboto14,
                modifier = Modifier.clickable { onSettingsClick() }
            )
        }
    }

}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(
        onSettingsClick = {}
    )
}
