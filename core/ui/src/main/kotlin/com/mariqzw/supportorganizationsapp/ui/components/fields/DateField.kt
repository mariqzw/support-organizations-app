package com.mariqzw.supportorganizationsapp.ui.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.errorLight
import com.mariqzw.supportorganizationsapp.ui.theme.onBackgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLightOpacity08
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceVariantLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceContainerHighLight
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        colors = DatePickerDefaults.colors(
            containerColor = surfaceContainerHighLight,
        ),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            ) {
                Text(text = "Подтвердить", color = onBackgroundLight)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Отменить", color = onBackgroundLight)
            }
        },
        content = {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = surfaceContainerHighLight,
                    titleContentColor = onSurfaceVariantLight,
                    headlineContentColor = onSurfaceVariantLight,
                    weekdayContentColor = onSurfaceVariantLight,
                    subheadContentColor = onSurfaceVariantLight,
                    navigationContentColor = onSurfaceVariantLight,
                    yearContentColor = onSurfaceVariantLight,
                    disabledYearContentColor = onSurfaceLight,
                    currentYearContentColor = onSurfaceVariantLight,
                    selectedYearContentColor = onSurfaceLightOpacity08,
                    disabledSelectedYearContentColor = onSurfaceLightOpacity08,
                    selectedYearContainerColor = primaryLight,
                    disabledSelectedYearContainerColor = onSurfaceLightOpacity08,
                    dayContentColor = onBackgroundLight,
                    disabledDayContentColor = onSurfaceLightOpacity08,
                    selectedDayContentColor = backgroundLight,
                    disabledSelectedDayContentColor = onSurfaceLightOpacity08,
                    selectedDayContainerColor = primaryLight,
                    disabledSelectedDayContainerColor = onSurfaceLightOpacity08,
                    todayContentColor = onBackgroundLight,
                    todayDateBorderColor = primaryLight,
                    dayInSelectionRangeContainerColor = onSurfaceLightOpacity08,
                    dayInSelectionRangeContentColor = onSurfaceLightOpacity08,
                    dividerColor = onSurfaceLightOpacity08,
                    dateTextFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = onBackgroundLight,
                        focusedContainerColor = surfaceContainerHighLight,
                        focusedPlaceholderColor = onBackgroundLight,
                        focusedLabelColor = onBackgroundLight,
                        focusedIndicatorColor = primaryLight,
                        unfocusedTextColor = onBackgroundLight,
                        unfocusedPlaceholderColor = onBackgroundLight,
                        unfocusedContainerColor = surfaceContainerHighLight,
                        unfocusedLabelColor = onSurfaceLightOpacity08,
                        unfocusedIndicatorColor = onSurfaceLightOpacity08,
                        errorTextColor = errorLight,
                        errorContainerColor = surfaceContainerHighLight,
                        errorCursorColor = errorLight,
                        errorIndicatorColor = errorLight,
                        errorLabelColor = errorLight,
                        errorSupportingTextColor = errorLight,
                        cursorColor = onBackgroundLight
                    )
                )
            )
        }
    )
}

@Composable
fun DatePickerTextField(
    modifier: Modifier = Modifier,
    label: String,
    initialDate: Long? = null,
    onDateSelected: (Long?) -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var isDatePickerVisible by remember { mutableStateOf(false) }

    val dateFormat = remember { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }
    val formattedDate = selectedDate?.let {
        dateFormat.format(Date(it))
    } ?: ""

    // Инпут для отображения выбранной даты
    PrimaryTextField(
        modifier = modifier.clickable { isDatePickerVisible = true },
        value = formattedDate,
        labelText = label,
        readOnly = true,
        onValueChange = {},
        trailingIconResId = R.drawable.calendar,
        onTrailingIconClick = { isDatePickerVisible = true }
    )

    // DatePicker
    if (isDatePickerVisible) {
        DatePickerModalInput(
            onDateSelected = { millis ->
                selectedDate = millis
                onDateSelected(millis)
                isDatePickerVisible = false
            },
            onDismiss = { isDatePickerVisible = false }
        )
    }
}

@Composable
@Preview
fun DatePickerModalInput() {
    Scaffold { innerPadding ->
        DatePickerTextField(
            modifier = Modifier.padding(innerPadding),
            label = "Дата",
            initialDate = null,
            onDateSelected = { selectedMillis ->
                // Здесь можно залогировать или обработать выбор даты в превью
                println("Selected date millis: $selectedMillis")
            }
        )
    }
}
