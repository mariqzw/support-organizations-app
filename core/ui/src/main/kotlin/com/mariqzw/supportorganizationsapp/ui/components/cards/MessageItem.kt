package com.mariqzw.supportorganizationsapp.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto14
import com.mariqzw.supportorganizationsapp.ui.theme.RegularRoboto16
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onPrimaryContainerLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceVariantLight
import com.mariqzw.supportorganizationsapp.ui.theme.primaryContainerLight

data class Message(val name: String, val text: String, val time: String)

@Composable
fun MessageItem(message: Message) {

    val dimensions = LocalDimensions.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
//            .padding(vertical = dimensions.verticalXXSmall)
            .background(backgroundLight)
            .clickable(indication = null,  // Отключаем визуальный эффект нажатия
                interactionSource = remember { MutableInteractionSource() },
                onClick = { })
    ) {
        Box(
            modifier = Modifier
                .size(dimensions.messageAvatar)
                .background(primaryContainerLight, shape = androidx.compose.foundation.shape.CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message.name.first().toString(),
                style = RegularRoboto16,
                color = onPrimaryContainerLight
            )
        }

        Spacer(modifier = Modifier.width(dimensions.horizontalMedium))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = message.name,
                    style = RegularRoboto16,
                    color = onSurfaceLight
                )

                Text(
                    text = message.time,
                    style = RegularRoboto14,
                    color = onSurfaceVariantLight
                )
            }

            Text(
                text = message.text,
                style = RegularRoboto14,
                color = onSurfaceVariantLight,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = dimensions.verticalXXSmall)
            )
        }
    }
}

@Composable
@Preview
fun MessageItemPreview() {
    MessageItem(
        Message(
            name = "Александра Федорова",
            text = "Я на станции Волоколамская",
            time = "21:29"
        )
    )
}
