package com.mariqzw.supportorganizationsapp.ui.components.fields

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.onPrimaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceVariantLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryContainerLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceContainerHighestLight
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {

    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    /** Determines whether the time picker is dial or input */
    var showDial by remember { mutableStateOf(true) }

    /** The icon used for the icon button that switches from dial to input */
    val toggleIcon = if (showDial) {
        Icons.Filled.Edit
    } else {
        Icons.Filled.Done
    }

    AdvancedTimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm(timePickerState) },
        toggle = {
            IconButton(
                onClick = { showDial = !showDial }, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Unspecified,
                    contentColor = onSurfaceVariantLight
                )
            ) {
                Icon(
                    imageVector = toggleIcon,
                    contentDescription = "Time picker type toggle",
                )
            }
        },
    ) {
        if (showDial) {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = surfaceContainerHighestLight,
                    clockDialSelectedContentColor = onSurfaceLight,
                    clockDialUnselectedContentColor = onSurfaceLight,
                    selectorColor = primaryLight,
                    containerColor = onPrimaryLight,
                    timeSelectorSelectedContainerColor = primaryContainerLight,
                    timeSelectorUnselectedContainerColor = surfaceContainerHighestLight,
                    timeSelectorSelectedContentColor = onSurfaceLight,
                    timeSelectorUnselectedContentColor = onSurfaceLight,
                )
            )
        } else {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = surfaceContainerHighestLight,
                    clockDialSelectedContentColor = onSurfaceLight,
                    clockDialUnselectedContentColor = onSurfaceLight,
                    selectorColor = primaryLight,
                    containerColor = onPrimaryLight,
                    timeSelectorSelectedContainerColor = primaryContainerLight,
                    timeSelectorUnselectedContainerColor = surfaceContainerHighestLight,
                    timeSelectorSelectedContentColor = onSurfaceLight,
                    timeSelectorUnselectedContentColor = onSurfaceLight,
                )
            )
        }
    }
}

@Composable
fun AdvancedTimePickerDialog(
    title: String = "Select Time",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = onPrimaryLight,
            tonalElevation = 6.dp,
            modifier =
                Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .background(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = onPrimaryLight
                    ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = RegularRoboto14,
                    color = primaryLight
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) { Text(text = "Cancel", color = primaryLight) }
                    TextButton(onClick = onConfirm) { Text(text = "OK", color = primaryLight) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimeFieldPreview() {
    AdvancedTimePickerExample(
        onConfirm = {},
        onDismiss = {},
    )
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerTextField(
    modifier: Modifier = Modifier,
    label: String = "Время",
    initialHour: Int? = null,
    initialMinute: Int? = null,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    var selectedHour by remember { mutableIntStateOf(initialHour ?: 0) }
    var selectedMinute by remember { mutableIntStateOf(initialMinute ?: 0) }
    var showDialog by remember { mutableStateOf(false) }

    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)

    PrimaryTextField(
        modifier = modifier
            .clickable { showDialog = true }
            .fillMaxWidth(),
        trailingIconResId = R.drawable.ic_time,
        value = formattedTime,
        labelText = label,
        readOnly = true,
        onValueChange = {},
        onTrailingIconClick = { showDialog = true }
    )

    if (showDialog) {
        AdvancedTimePickerExample(
            onConfirm = { timePickerState ->
                selectedHour = timePickerState.hour
                selectedMinute = timePickerState.minute
                onTimeSelected(selectedHour, selectedMinute)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
@Preview
fun AdvancedTimePickerPreview() {
    var selectedTime by remember { mutableStateOf("") }
    AdvancedTimePickerTextField(
        onTimeSelected = { hour, minute ->
            selectedTime = String.format("%02d:%02d", hour, minute)
        },
        label = "Время"
    )
}
