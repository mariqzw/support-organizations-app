package com.mariqzw.supportorganizationsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val RegularTextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    color = onBackgroundLight,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumTextStyle = TextStyle(
    fontWeight = FontWeight.Medium,
    color = onBackgroundLight,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val SemiBoldTextStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    color = onBackgroundLight,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldTextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    color = onBackgroundLight,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val RegularText12 = RegularTextStyle.copy(fontSize = 12.sp)
val RegularText14 = RegularTextStyle.copy(fontSize = 14.sp)
val RegularText16 = RegularTextStyle.copy(fontSize = 16.sp)

val MediumText14 = MediumTextStyle.copy(fontSize = 14.sp)
val MediumText12 = MediumTextStyle.copy(fontSize = 12.sp)
val MediumText16 = MediumTextStyle.copy(fontSize = 16.sp)

val SemiBoldText12 = SemiBoldTextStyle.copy(fontSize = 12.sp)
val SemiBoldText14 = SemiBoldTextStyle.copy(fontSize = 14.sp)
val SemiBoldText16 = SemiBoldTextStyle.copy(fontSize = 16.sp)
val SemiBoldText24 = SemiBoldTextStyle.copy(fontSize = 24.sp)

val BoldText14 = BoldTextStyle.copy(fontSize = 14.sp)
val BoldText16 = BoldTextStyle.copy(fontSize = 16.sp)
val BoldText22 = BoldTextStyle.copy(fontSize = 22.sp)
