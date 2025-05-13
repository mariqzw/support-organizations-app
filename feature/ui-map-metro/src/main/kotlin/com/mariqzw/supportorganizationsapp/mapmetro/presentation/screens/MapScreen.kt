package com.mariqzw.supportorganizationsapp.mapmetro.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mariqzw.supportorganizationsapp.mapmetro.R

@Composable
fun MapScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val density = LocalDensity.current

    // Преобразование размеров в пиксели
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }

    // Состояния: масштаб и смещение
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    // Ограничения на масштаб
    val minScale = 1f
    val maxScale = 5f

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        // Изменяем масштаб в пределах допустимых значений
        scale = (scale * zoomChange).coerceIn(minScale, maxScale)

        // Рассчёт размеров изображения с учётом масштаба
        val scaledWidth = screenWidthPx * scale
        val scaledHeight = screenHeightPx * scale

        // Вычисляем границы смещения с учётом масштаба и экрана
        val maxX = (scaledWidth - screenWidthPx) / 2
        val maxY = (scaledHeight - screenHeightPx) / 2

        // Обновляем смещение с учётом ограничений
        val newOffset = offset + offsetChange
        val clampedX = newOffset.x.coerceIn(-maxX, maxX)
        val clampedY = newOffset.y.coerceIn(-maxY, maxY)

        offset = Offset(clampedX, clampedY)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEDF6F6))
            .transformable(state = state)
    ) {
        Image(
            painter = painterResource(id = R.drawable.metro_map_pc),
            contentDescription = "Схема московского метро",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .fillMaxSize()
        )
    }
}

/**
 * Рассчитывает допустимые границы смещения.
 */
private fun calculateRestrictedOffset(
    offset: Offset,
    scale: Float,
    screenSize: IntSize,
    imageSize: IntSize
): Offset {
    val scaledImageWidth = imageSize.width * scale
    val scaledImageHeight = imageSize.height * scale

    val maxOffsetX = (scaledImageWidth - screenSize.width) / 2
    val maxOffsetY = (scaledImageHeight - screenSize.height) / 2

    val restrictedX = offset.x.coerceIn(-maxOffsetX, maxOffsetX)
    val restrictedY = offset.y.coerceIn(-maxOffsetY, maxOffsetY)

    return Offset(restrictedX, restrictedY)
}

@Composable
@Preview
fun MapScreenPreview() {
    MapScreen()
}
