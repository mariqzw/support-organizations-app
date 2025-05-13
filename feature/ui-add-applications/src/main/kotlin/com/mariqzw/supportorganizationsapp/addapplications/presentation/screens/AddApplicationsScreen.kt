package com.mariqzw.supportorganizationsapp.addapplications.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.AdvancedTimePickerTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.DatePickerTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.SupportOrganizationsAppTheme
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight

@SuppressLint("DefaultLocale")
@Composable
fun AddApplicationScreen(
    onSendClick: () -> Unit
) {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var startPoint by remember { mutableStateOf("") }
    var endPoint by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    val dimensions = LocalDimensions.current
//    val scrollState = rememberScrollState()

    SupportOrganizationsAppTheme {
        Surface(
            color = backgroundLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                .verticalScroll(scrollState)
                    .padding(
                        horizontal = dimensions.horizontalMedium,
                        vertical = dimensions.verticalXSmall
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(dimensions.verticalMedium)
            ) {
                DatePickerTextField(
                    label = "Дата",
                    onDateSelected = {
                        // Do nothing
                    }
                )

                AdvancedTimePickerTextField(
                    label = "Время",
                    onTimeSelected = { hour, minute ->
                        time = String.format("%02d:%02d", hour, minute)
                    }
                )

                PrimaryTextField(
                    value = startPoint,
                    labelText = "Станция отправления",
                    onValueChange = {
                        startPoint = it
                    }
                )

                PrimaryTextField(
                    value = endPoint,
                    labelText = "Станция назначения",
                    onValueChange = {
                        endPoint = it
                    }
                )

                PrimaryTextField(
                    value = comment,
                    labelText = "Комментарий",
                    onValueChange = {
                        comment = it
                    },
                    singleLine = false,
                    maxLines = 10,
                    modifier = Modifier
                        .weight(1f)
                )

                PrimaryButton(
                    text = "Отправить",
                    onButtonClick = onSendClick
                )
            }
        }
    }

}

@Composable
@Preview
fun AddApplicationScreenPreview() {
    AddApplicationScreen(
        onSendClick = {
            // Do nothing
        }
    )
}
