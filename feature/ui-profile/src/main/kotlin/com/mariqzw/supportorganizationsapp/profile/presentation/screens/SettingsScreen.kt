package com.mariqzw.supportorganizationsapp.profile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.components.buttons.PrimaryButton
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onPrimaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.outlineLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryContainerLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceContainerHighestLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit
) {
    val dimensions = LocalDimensions.current

    Surface(
        color = backgroundLight
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensions.horizontalMedium, vertical = dimensions.verticalLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(dimensions.verticalLarge)
        ) {
            Text(
                text = "Размер шрифта",
                style = RegularRoboto16
            )

            Slider(
                value = sliderValue,
                onValueChange = { onSliderValueChange(it) },
                colors = SliderDefaults.colors(
                    thumbColor = primaryLight,
                    activeTrackColor = primaryLight,
                    inactiveTrackColor = primaryContainerLight
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensions.verticalXXSmall),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Темная тема", style = RegularRoboto16)
                Switch(
                    checked = checked,
                    onCheckedChange = { onCheckedChange(it) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = onPrimaryLight,
                        checkedTrackColor = primaryLight,
                        uncheckedThumbColor = outlineLight,
                        uncheckedTrackColor = surfaceContainerHighestLight
                    )
                )
                Text(text = "Светлая тема", style = RegularRoboto16)
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Сохранить",
                onButtonClick = {}
            )

            Text(
                text = "Выйти из аккаунта",
                style = RegularRoboto14,
                modifier = Modifier.clickable {}
            )
        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(
        sliderValue = 0.5f,
        onCheckedChange = {},
        checked = true,
        onSliderValueChange = {},
        onNavigateBack = {}
    )
}
