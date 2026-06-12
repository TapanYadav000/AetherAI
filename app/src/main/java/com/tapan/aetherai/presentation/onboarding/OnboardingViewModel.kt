package com.tapan.aetherai.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.aetherai.domain.model.PersonalityTrait
import com.tapan.aetherai.domain.model.UserProfile
import com.tapan.aetherai.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())

    val state: StateFlow<OnboardingState> =
        _state.asStateFlow()

    fun nextPage() {
        _state.update {
            it.copy(
                currentPage = (it.currentPage + 1).coerceAtMost(2)
            )
        }
    }

    fun previousPage() {
        _state.update {
            it.copy(
                currentPage = (it.currentPage - 1).coerceAtLeast(0)
            )
        }
    }

    fun updateName(value: String) {
        _state.update {
            it.copy(name = value)
        }
    }

    fun updateAge(value: String) {
        _state.update {
            it.copy(age = value)
        }
    }

    fun updatePhone(value: String) {
        _state.update {
            it.copy(phoneNumber = value)
        }
    }

    fun updateOtp(value: String) {
        _state.update {
            it.copy(otp = value)
        }
    }

    fun updateOtpVerified(value: Boolean) {
        _state.update {
            it.copy(
                otpVerified = value
            )
        }
    }

    fun toggleTrait(trait: PersonalityTrait) {

        val current = _state.value.selectedTraits

        val updated = when {

            current.contains(trait) ->
                current - trait

            current.size < 3 ->
                current + trait

            else ->
                current
        }
        _state.update {
            it.copy(selectedTraits = updated)
        }
    }
    fun finishOnboarding() {

        viewModelScope.launch {

            val profile = UserProfile(
                name = _state.value.name,
                age = _state.value.age.toIntOrNull() ?: 0,
                phoneNumber = _state.value.phoneNumber,
                personalityTraits = _state.value.selectedTraits.toList()
            )

            userRepository.saveUserProfile(profile)

            _state.update {
                it.copy(isCompleted = true)
            }
        }
    }
}