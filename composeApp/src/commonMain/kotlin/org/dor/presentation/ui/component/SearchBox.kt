package org.dor.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SearchBox(
    modifier: Modifier = Modifier,
    placeholder: String = "Enter IP address",
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 32.dp))
            .background(color = Color.White.copy(alpha = 0.3f))
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(value = Color(color = 0xFF64B5F6)),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),
            textStyle = LocalTextStyle.current.copy(
                color = Color.White,
                fontWeight = FontWeight.Normal,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = placeholder,
                            color = Color.White.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                    }

                    innerTextField()
                }
            }
        )
    }
}