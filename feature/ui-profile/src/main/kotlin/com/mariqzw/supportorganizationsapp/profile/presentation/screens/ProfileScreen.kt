package com.mariqzw.supportorganizationsapp.profile.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.profile.R
import com.mariqzw.supportorganizationsapp.profile.presentation.viewmodels.ProfileScreenViewModel
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit,
    viewModel: ProfileScreenViewModel = koinViewModel(),
) {
    val dimensions = LocalDimensions.current

    val name = viewModel.name.collectAsState()
    val surname = viewModel.surname.collectAsState()
    val phoneNumber = viewModel.phoneNumber.collectAsState()
    val email = viewModel.email.collectAsState()

    val alertMessage = viewModel.alertMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserData()
    }

    Surface(color = backgroundLight) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensions.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensions.verticalSmall, Alignment.CenterVertically)
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
                onValueChange = viewModel::onNameChanged,
                labelText = "Имя",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = surname.value,
                onValueChange = viewModel::onSurnameChanged,
                labelText = "Фамилия",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = phoneNumber.value,
                onValueChange = viewModel::onPhoneNumberChanged,
                labelText = "Номер телефона",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryTextField(
                value = email.value,
                onValueChange = viewModel::onEmailChanged,
                labelText = "Электронная почта",
                trailingIconResId = R.drawable.ic_outline_create,
                errorText = "Введено некорректное значение или символы"
            )

            PrimaryButton(
                text = "Сохранить",
                onButtonClick = {
                    viewModel.updateUser()
                }
            )

            Text(
                text = "Настройки",
                style = RegularRoboto14,
                modifier = Modifier.clickable { onSettingsClick() }
            )
        }

        alertMessage.value?.let { message ->
            AlertDialog(
                onDismissRequest = { viewModel.dismissAlert() },
                confirmButton = {
                    TextButton(onClick = { viewModel.dismissAlert() }) {
                        Text("ОК")
                    }
                },
                text = {
                    Text(text = message)
                }
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
