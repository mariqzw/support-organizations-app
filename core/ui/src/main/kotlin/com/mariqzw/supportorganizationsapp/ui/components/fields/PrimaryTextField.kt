package com.mariqzw.supportorganizationsapp.ui.components.fields

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto12
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.errorLight
import com.mariqzw.supportorganizationsapp.ui.theme.onBackgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceVariantLight
import timber.log.Timber

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    errorText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    @DrawableRes trailingIconResId: Int? = null,
    trailingIconModifier: Modifier = Modifier,
    onTrailingIconClick: () -> Unit = {},
    singleLine: Boolean = true,
    maxLines: Int = 1,
    readOnly: Boolean = false
) {
    val dimensions = LocalDimensions.current

    var isFocused by remember { mutableStateOf(false) }

    var borderColor by remember { mutableStateOf(onSurfaceVariantLight) }
    var labelColor by remember { mutableStateOf(onSurfaceVariantLight) }

    when {
        isError -> {
            borderColor = errorLight
            labelColor = errorLight
        }

        isFocused -> {
            borderColor = onSurfaceLight
            labelColor = onBackgroundLight
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensions.verticalXXSmall)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                    isFocused = state.isFocused
                },
            label = {
                Text(
                    text = labelText,
                    style = MediumRoboto12
                )
            },
            placeholder = {
                if (!isFocused && value.isEmpty()) {
                    Text(
                        text = labelText,
                        style = RegularRoboto16
                    )
                }
            },
            isError = isError,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = {
                trailingIconResId?.let { trailingIconResId ->
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(
                            painter = painterResource(id = trailingIconResId),
                            modifier = trailingIconModifier,
                            contentDescription = null
                        )
                    }
                }
            },
            textStyle = RegularRoboto16,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = backgroundLight,
                unfocusedBorderColor = borderColor,
                unfocusedLabelColor = labelColor,
                unfocusedPlaceholderColor = labelColor,
                focusedContainerColor = backgroundLight,
                focusedBorderColor = borderColor,
                focusedLabelColor = labelColor,
                focusedPlaceholderColor = labelColor,
                errorContainerColor = backgroundLight,
                errorBorderColor = errorLight,
                errorLabelColor = errorLight,
                cursorColor = borderColor
            )
        )

        if (isError) {
            if (errorText != null) {
                Text(
                    text = errorText,
                    color = errorLight,
                    style = MediumRoboto12,
                    modifier = Modifier.padding(dimensions.verticalXXSmall)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    Surface(
        color = backgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PrimaryTextField(
                value = text,
                onValueChange = {
                    text = it
                    Timber.d("textForField", text)
                },
                labelText = "Имя",
                isError = false,
                errorText = "Введено некорректное значение",
            )

            PrimaryTextField(
                value = text,
                onValueChange = {
                    text = it
                    Timber.d("textForField", text)
                },
                labelText = "Имя",
                isError = true,
                errorText = "Введено некорректное значение",
            )
        }
    }
}
