package com.tapan.aetherai.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.aetherai.data.audio.AudioRecorder
import com.tapan.aetherai.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val audioRecorder = AudioRecorder()

    val amplitude = audioRecorder.amplitude

    fun startListening() {
        audioRecorder.startListening()
    }

    fun stopListening() {
        audioRecorder.stopListening()
    }
    val uiState: StateFlow<HomeUiState> =
        userRepository.getUserProfile()
            .map { profile ->

                HomeUiState(
                    profile = profile,
                    isLoading = false
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState()
            )
}