package com.mariqzw.supportorganizationsapp.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.MediumRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceContainerLowLight

@Composable
fun PrimaryCard(
    date: String,
    status: String,
    startPoint: String,
    endPoint: String,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLowLight,
            contentColor = onSurfaceLight
        ),
        shape = RoundedCornerShape(dimensions.roundedShape),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.defaultElevation),
    ) {
        val contentColor = LocalContentColor.current

        Column(
            modifier = Modifier
                .padding(dimensions.verticalSmall)
        ) {
            Spacer(modifier = Modifier.height(dimensions.verticalXXSmall))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
                    style = MediumRoboto16,
                    color = contentColor
                )
                Text(
                    text = status,
                    style = MediumRoboto16,
                    color = contentColor
                )
            }
            Spacer(modifier = Modifier.height(dimensions.verticalXSmall))
            Text(
                text = "$startPoint - $endPoint",
                style = RegularRoboto14,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(dimensions.verticalXXSmall))
        }
    }
}

@Composable
@Preview
fun PrimaryCardPreview() {
    Surface(color = backgroundLight) {
        PrimaryCard(
            date = "27.03.2024",
            status = "Принята",
            startPoint = "Марьина Роща",
            endPoint = "Чертановская"
        )
    }
}
