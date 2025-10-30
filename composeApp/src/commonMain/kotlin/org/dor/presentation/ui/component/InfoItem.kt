package org.dor.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun InfoItem(title: String, value: String, isDividerVisible: Boolean = true) {
    if (value.isBlank()) return

    Row(
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp
        )
    ) {
        Text(
            text = title,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )

        Text(
            modifier = Modifier.weight(weight = 1f),
            text = value,
            textAlign = TextAlign.End,
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 12.sp
        )
    }

    if (isDividerVisible) {
        HorizontalDivider(
            thickness = 0.2.dp,
            color = Color.White.copy(alpha = 0.3f)
        )
    }
}