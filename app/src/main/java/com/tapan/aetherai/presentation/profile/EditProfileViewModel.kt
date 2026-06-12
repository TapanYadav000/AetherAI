package com.tapan.aetherai.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.aetherai.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditProfileUiState()
    )

    val uiState: StateFlow<EditProfileUiState> =
        _uiState.asStateFlow()

    init {

        viewModelScope.launch {

            userRepository.getUserProfile()
                .collect { profile ->

                    _uiState.update {

                        it.copy(
                            name = profile.name,
                            age = profile.age.toString(),
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun updateName(value: String) {

        _uiState.update {
            it.copy(name = value)
        }
    }

    fun updateAge(value: String) {

        _uiState.update {
            it.copy(age = value)
        }
    }

    fun saveProfile() {

        viewModelScope.launch {

            userRepository.updateUserProfile(
                name = _uiState.value.name,
                age = _uiState.value.age.toIntOrNull() ?: 0
            )

            _uiState.update {
                it.copy(isSaved = true)
            }
        }
    }
}