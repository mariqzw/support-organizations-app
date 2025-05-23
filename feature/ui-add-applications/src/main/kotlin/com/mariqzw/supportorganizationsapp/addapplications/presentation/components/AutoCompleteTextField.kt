import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
    onSuggestionSelected: (String) -> Unit,
    errorText: String? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
) {
    val dimensions = LocalDimensions.current
    var isFocused by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val filteredSuggestions = remember(value, suggestions) {
        suggestions.filter { it.contains(value, ignoreCase = true) }
    }

    val borderColor by remember {
        derivedStateOf {
            when {
                isError -> errorLight
                isFocused -> onSurfaceLight
                else -> onSurfaceVariantLight
            }
        }
    }

    val labelColor by remember {
        derivedStateOf {
            when {
                isError -> errorLight
                isFocused -> onBackgroundLight
                else -> onSurfaceVariantLight
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensions.verticalXXSmall)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded && filteredSuggestions.isNotEmpty(),
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                    expanded = true
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                        if (!it.isFocused) expanded = false
                    },
                label = { Text(text = labelText, style = MediumRoboto12) },
                placeholder = {
                    if (!isFocused && value.isEmpty()) {
                        Text(text = labelText, style = RegularRoboto16)
                    }
                },
                isError = isError,
                singleLine = singleLine,
                maxLines = maxLines,
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

            ExposedDropdownMenu(
                expanded = expanded && value.isNotEmpty(),
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensions.horizontalMedium)
                    .heightIn(max = 104.dp)
            ) {
                if (filteredSuggestions.isEmpty()) {
                    DropdownMenuItem(
                        text = {
                            Text("Ничего не найдено", color = onSurfaceVariantLight)
                        },
                        onClick = {},
                        enabled = false
                    )
                } else {
                    filteredSuggestions.forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion, color = onBackgroundLight) },
                            onClick = {
                                onSuggestionSelected(suggestion)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = errorLight,
                style = MediumRoboto12,
                modifier = Modifier.padding(dimensions.verticalXXSmall)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AutoCompleteTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    val suggestions = listOf("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург")

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
            AutoCompleteTextField(
                value = text,
                onValueChange = { text = it },
                onSuggestionSelected = { text = it },
                labelText = "Город",
                suggestions = suggestions
            )
        }
    }
}
