package com.tapan.aetherai.presentation.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.aetherai.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _hasProfile = MutableStateFlow<Boolean?>(null)

    val hasProfile: StateFlow<Boolean?> =
        _hasProfile.asStateFlow()

    init {

        viewModelScope.launch {

            userRepository.getUserProfile()
                .collect { profile ->

                    _hasProfile.value =
                        profile.name.isNotBlank()
                }
        }
    }
}