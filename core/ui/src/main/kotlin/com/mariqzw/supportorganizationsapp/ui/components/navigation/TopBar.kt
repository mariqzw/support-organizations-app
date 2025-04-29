package com.mariqzw.supportorganizationsapp.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onBackgroundLight

@Composable
fun TopBar(
    title: String,
    showArrow: Boolean = false,
    onClick: () -> Unit
) {
    val dimensions = LocalDimensions.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.topBarHeight)
            .background(backgroundLight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MediumRoboto16,
            color = onBackgroundLight
        )
        if (showArrow) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                contentDescription = "Назад",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(dimensions.horizontalMedium)
                    .clickable { onClick() }
            )
        }
    }
}

@Composable
@Preview
fun TopBarPreview() {
    Surface(
        color = Color.White
    ) {
        TopBar(
            title = "Заявка на сопровождение",
            showArrow = true,
            onClick = { }
        )
    }
}
