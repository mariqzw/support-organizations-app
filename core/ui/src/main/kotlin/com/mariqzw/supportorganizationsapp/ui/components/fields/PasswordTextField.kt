package com.mariqzw.supportorganizationsapp.ui.components.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.ui.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    errorText: String,
    isError: Boolean,
    onTextChange: (password: String) -> Unit,
    maxCharCount: Int? = null
) {
    var passwordValue by remember { mutableStateOf(value) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    PrimaryTextField(
        modifier = modifier,
        value = passwordValue,
        labelText = labelText,
        errorText = errorText,
        isError = isError,
        onValueChange = { textValue ->
            if (textValue.length <= (maxCharCount ?: (textValue.length + 1))) {
                passwordValue = textValue
                onTextChange(textValue)
            }
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIconResId = if (isPasswordVisible) {
            R.drawable.icon_visibility_on
        } else {
            R.drawable.icon_visibility_off
        },
        onTrailingIconClick = {
            isPasswordVisible = !isPasswordVisible
        },
        keyboardType = KeyboardType.Password
    )
}

@Composable
@Preview
fun PasswordTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    val isError = text.length < 6 && text.isNotEmpty()

    Surface {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            PasswordTextField(
                value = text,
                labelText = "Пароль",
                isError = isError,
                errorText = "Пароль должен содержать минимум 6 символов",
                onTextChange = {
                    text = it
                }
            )
        }
    }
}
