package com.mariqzw.supportorganizationsapp.addapplications.presentation.screens

import AutoCompleteTextField
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.addapplications.R
import com.mariqzw.supportorganizationsapp.addapplications.presentation.viewmodels.AddApplicationViewModel
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.components.fields.AdvancedTimePickerTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.DatePickerTextField
import com.mariqzw.supportorganizationsapp.ui.components.fields.PrimaryTextField
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.SupportOrganizationsAppTheme
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("DefaultLocale")
@Composable
fun AddApplicationScreen(
    viewModel: AddApplicationViewModel = koinViewModel()
) {
    val date by viewModel.date.collectAsState()
    val time by viewModel.time.collectAsState()
    val startPoint by viewModel.startPoint.collectAsState()
    val endPoint by viewModel.endPoint.collectAsState()
    val comment by viewModel.comment.collectAsState()
    val alertMessage by viewModel.alertMessage.collectAsState()

    val dimensions = LocalDimensions.current

    val context = LocalContext.current

    // Читаем stations из raw прямо здесь
    val stations = remember {
        context.resources.openRawResource(R.raw.stations).bufferedReader().useLines { lines ->
            lines.map { it.trim() }.filter { it.isNotEmpty() }.toList()
        }
    }

    SupportOrganizationsAppTheme {
        Surface(color = backgroundLight) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensions.horizontalMedium,
                        vertical = dimensions.verticalXSmall
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensions.verticalMedium)
            ) {
                DatePickerTextField(
                    label = "Дата",
                    initialDate = date.takeIf { it.isNotBlank() }?.let {
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it)?.time
                    },
                    onDateSelected = { millis -> viewModel.onDateChanged(millis) }
                )

                AdvancedTimePickerTextField(
                    label = "Время",
                    onTimeSelected = { hour, minute ->
                        viewModel.onTimeChanged(String.format("%02d:%02d", hour, minute))
                    }
                )

                AutoCompleteTextField(
                    value = startPoint,
                    onValueChange = viewModel::onStartPointChanged,
                    onSuggestionSelected = viewModel::onStartPointChanged,
                    labelText = "Станция отправления",
                    suggestions = stations
                )

                AutoCompleteTextField(
                    value = endPoint,
                    onValueChange = viewModel::onEndPointChanged,
                    onSuggestionSelected = viewModel::onEndPointChanged,
                    labelText = "Станция назначения",
                    suggestions = stations
                )

//                PrimaryTextField(
//                    value = startPoint,
//                    labelText = "Станция отправления",
//                    onValueChange = viewModel::onStartPointChanged
//                )
//
//                PrimaryTextField(
//                    value = endPoint,
//                    labelText = "Станция назначения",
//                    onValueChange = viewModel::onEndPointChanged
//                )

                PrimaryTextField(
                    value = comment,
                    labelText = "Комментарий",
                    onValueChange = viewModel::onCommentChanged,
                    singleLine = false,
                    maxLines = 10,
                    modifier = Modifier.weight(1f)
                )

                PrimaryButton(
                    text = "Отправить",
                    onButtonClick = { viewModel.sendApplication() }
                )
            }

            val message = alertMessage
            if (message != null) {
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
}

@Composable
@Preview
fun AddApplicationScreenPreview() {
    AddApplicationScreen(
    )
}
