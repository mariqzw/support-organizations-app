package com.mariqzw.supportorganizationsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mariqzw.supportorganizationsapp.ui.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val SupportOrganizationsAppRobotoFamily = FontFamily(
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

private val RegularRobotoStyle = TextStyle(
    fontFamily = SupportOrganizationsAppRobotoFamily,
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

private val MediumRobotoStyle = TextStyle(
    fontFamily = SupportOrganizationsAppRobotoFamily,
    fontWeight = FontWeight.Medium,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val RegularRoboto32 = RegularRobotoStyle.copy(
    fontSize = 32.sp,
    lineHeight = 40.sp
)
val RegularRoboto24 = RegularRobotoStyle.copy(
    fontSize = 24.sp,
    lineHeight = 32.sp
)
val RegularRoboto16 = RegularRobotoStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val RegularRoboto14 = RegularRobotoStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp
)

val MediumRoboto16 = MediumRobotoStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp
)
val MediumRoboto14 = MediumRobotoStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp
)
val MediumRoboto12 = MediumRobotoStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp
)
