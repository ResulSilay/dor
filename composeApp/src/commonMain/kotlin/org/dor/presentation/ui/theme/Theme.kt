package org.dor.presentation.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DefaultColorScheme = darkColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    secondary = Color(0xFF64B5F6),
    onSecondary = Color.Black,
    background = Color(0xFF0D47A1),
    onBackground = Color.White,
    surface = Color(0xFF1565C0),
    onSurface = Color.White
)


@Composable
fun DorTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = DefaultColorScheme,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
