package com.luisnafate.examen3.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = F1Red,
    onPrimary = F1White,
    primaryContainer = F1RedDark,
    onPrimaryContainer = F1White,
    secondary = F1GrayLight,
    onSecondary = F1White,
    secondaryContainer = F1Gray,
    onSecondaryContainer = F1White,
    tertiary = GoldMedal,
    onTertiary = F1Black,
    error = F1RedLight,
    background = F1Black,
    onBackground = F1White,
    surface = F1GrayDark,
    onSurface = F1White
)

private val LightColorScheme = lightColorScheme(
    primary = F1Red,
    onPrimary = F1White,
    primaryContainer = F1RedLight,
    onPrimaryContainer = F1Black,
    secondary = F1Gray,
    onSecondary = F1White,
    secondaryContainer = F1GrayLight,
    onSecondaryContainer = F1White,
    tertiary = GoldMedal,
    onTertiary = F1Black,
    error = F1Red,
    background = F1White,
    onBackground = F1Black,
    surface = F1White,
    onSurface = F1Black
)

@Composable
fun Examen3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}