package com.tapan.aetherai.presentation.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AuraCircle(
    state: AuraState,
    modifier: Modifier = Modifier
) {

    val infiniteTransition = rememberInfiniteTransition()

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scale = when (state) {

        AuraState.Idle -> pulse

        is AuraState.Listening -> {
            1f + (state.amplitude * 0.5f)
        }
    }

    Canvas(
        modifier = modifier.size(220.dp)
    ) {

        val radius = size.minDimension / 4f

        drawCircle(
            color = Color(0xFF6C63FF).copy(alpha = 0.15f),
            radius = radius * 2f * scale,
            center = Offset(
                size.width / 2f,
                size.height / 2f
            )
        )

        drawCircle(
            color = Color(0xFF6C63FF).copy(alpha = 0.30f),
            radius = radius * 1.5f * scale,
            center = Offset(
                size.width / 2f,
                size.height / 2f
            )
        )

        drawCircle(
            color = Color(0xFF6C63FF),
            radius = radius * scale,
            center = Offset(
                size.width / 2f,
                size.height / 2f
            )
        )
    }
}