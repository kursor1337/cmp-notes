package com.kursor.chronicles_of_ww2.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_Black
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_Bold
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_Light
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_Medium
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_Regular
import cmp_notes.composeapp.core.generated.resources.Merriweather_24pt_SemiBold
import cmp_notes.composeapp.core.generated.resources.Res
import com.kursor.chronicles_of_ww2.core.theme.custom.BodyTypography
import com.kursor.chronicles_of_ww2.core.theme.custom.ButtonTypography
import com.kursor.chronicles_of_ww2.core.theme.custom.CaptionTypography
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTypography
import com.kursor.chronicles_of_ww2.core.theme.custom.TitleTypography
import org.jetbrains.compose.resources.Font

val merriweatherFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.Merriweather_24pt_Light, FontWeight.Light),
        Font(Res.font.Merriweather_24pt_Regular, FontWeight.Normal),
        Font(Res.font.Merriweather_24pt_Medium, FontWeight.Medium),
        Font(Res.font.Merriweather_24pt_Bold, FontWeight.Bold),
        Font(Res.font.Merriweather_24pt_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Merriweather_24pt_Bold, FontWeight.Bold),
        Font(Res.font.Merriweather_24pt_Black, FontWeight.Black),
    )

val AppTypography: CustomTypography
    @Composable
    get() = CustomTypography(
        title = TitleTypography(
            heading = TextStyle(
                fontSize = 32.sp,
                fontFamily = merriweatherFontFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            ),
            mediumBold = TextStyle(
                fontSize = 20.sp,
                fontFamily = merriweatherFontFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )
        ),
        body = BodyTypography(
            regular = TextStyle(
                fontSize = 16.sp,
                fontFamily = merriweatherFontFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )
        ),
        button = ButtonTypography(
            bold = TextStyle(
                fontSize = 16.sp,
                fontFamily = merriweatherFontFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
        ),
        caption = CaptionTypography(
            regular = TextStyle(
                fontSize = 12.sp,
                fontFamily = merriweatherFontFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp
            )
        )
    )