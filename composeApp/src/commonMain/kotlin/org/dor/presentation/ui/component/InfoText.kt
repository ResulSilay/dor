package org.dor.presentation.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun InfoText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    )
}