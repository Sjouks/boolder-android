package com.boolder.boolder.view.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BoolderTheme(
    content: @Composable () -> Unit
) {
    val boolderColorScheme = MaterialTheme.colorScheme.copy(
        primary = Color(0xFF65C466),
        onPrimary = Color.White,
        secondary = Color(0xFF65C466),
        onSecondary = Color(0xFF018786),
        surface = Color.White,
        onSurface = Color.Black,
        onSurfaceVariant = Color(0xFFA5A5A5),
        outline = Color(0xFFE5E5E5),
    )

    MaterialTheme(
        colorScheme = boolderColorScheme,
        content = content
    )
}
