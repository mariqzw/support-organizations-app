package com.mariqzw.supportorganizationsapp.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.onPrimaryLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryLight

@Composable
fun PrimaryButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val dimensions = LocalDimensions.current

    Button(
        modifier = modifier.wrapContentSize(),
        onClick = onButtonClick,
        contentPadding = PaddingValues(
            horizontal = dimensions.horizontalLarge,
            vertical = dimensions.verticalXSmall
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryLight,
            contentColor = onPrimaryLight
        )
    ) {
        val localContentColorButton = LocalContentColor.current
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(dimensions.verticalLarge)
                    .aspectRatio(1f)
                    .align(Alignment.CenterVertically),
                color = localContentColorButton,
                strokeCap = StrokeCap.Round,
                strokeWidth = dimensions.circularStrokeWith
            )
        } else {
            Text(
                text = text,
                style = MediumRoboto14,
                color = localContentColorButton
            )
        }
    }
}

@Composable
@Preview
fun PrimaryButtonPreview() {
    Surface {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            PrimaryButton(
                modifier = Modifier,
                text = "Войти",
                isLoading = false,
                onButtonClick = {
                    // Do nothing
                }
            )

            PrimaryButton(
                modifier = Modifier,
                text = "Зарегистрироваться",
                isLoading = false,
                onButtonClick = {
                    // Do nothing
                }
            )

            PrimaryButton(
                modifier = Modifier,
                text = "Войти",
                isLoading = true,
                onButtonClick = {
                    // Do nothing
                }
            )
        }
    }
}
