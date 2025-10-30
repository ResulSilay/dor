package org.dor.presentation.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
internal fun ScanEffect(
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    baseColor: Color = Color.White.copy(alpha = 0.5f),
    rippleCount: Int = 3,
    strokeWidth: Float = 130f,
    searchContent: @Composable (() -> Unit)? = null,
) {
    val rippleStates = remember { List(size = rippleCount) { Animatable(initialValue = 0f) } }

    LaunchedEffect(isActive) {
        if (isActive) {
            rippleStates.forEachIndexed { index, anim ->
                launch {
                    while (isActive) {
                        anim.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(
                                durationMillis = 2000,
                                easing = LinearEasing,
                                delayMillis = index * (2000 / rippleCount)
                            )
                        )
                        anim.snapTo(targetValue = 0f)
                    }
                }
            }
        }
    }

    val searchAlpha by animateFloatAsState(
        targetValue = if (isActive) 0f else 1f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isActive) {
            Canvas(
                modifier = Modifier.size(size = 400.dp)
            ) {
                val center = Offset(
                    x = size.width / 2f,
                    y = size.height / 2f
                )
                val maxRadius = size.minDimension / 2f

                rippleStates.forEach { anim ->
                    val radius = maxRadius * 0.3f + maxRadius * 0.7f * anim.value
                    val alpha = 0.25f * (1 - anim.value)

                    drawCircle(
                        color = baseColor.copy(alpha = alpha),
                        style = Stroke(width = strokeWidth),
                        radius = radius,
                        center = center
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(size = 50.dp)
                    .background(baseColor, shape = CircleShape)
            )
        } else if (searchContent != null) {
            Box(
                modifier = Modifier.graphicsLayer { this.alpha = searchAlpha }
            ) {
                searchContent()
            }
        }
    }
}
