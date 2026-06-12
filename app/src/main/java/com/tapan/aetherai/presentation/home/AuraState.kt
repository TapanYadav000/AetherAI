package com.tapan.aetherai.presentation.home

sealed class AuraState {

    data object Idle : AuraState()

    data class Listening(
        val amplitude: Float
    ) : AuraState()
}