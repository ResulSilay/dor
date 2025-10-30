package org.dor.presentation.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun LogoText() {
    Text(
        text = "dor",
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = (-2).sp,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.1f),
                offset = Offset(x = 2f, y = 2f),
                blurRadius = 80f
            ),
            fontFamily = FontFamily.SansSerif
        ),
        textAlign = TextAlign.Center
    )
}
