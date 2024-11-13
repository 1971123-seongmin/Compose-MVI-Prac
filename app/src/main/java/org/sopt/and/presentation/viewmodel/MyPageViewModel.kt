package org.sopt.and.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.user.GetMyHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val geyMyHobbyUseCase: GetMyHobbyUseCase
) : ViewModel() {

    private val _userHobby = MutableStateFlow<String>("")
    val userHobby: StateFlow<String> = _userHobby

    init {
        geyMyHobby()
    }

    private fun geyMyHobby() {
        viewModelScope.launch {
            geyMyHobbyUseCase().onSuccess {
                _userHobby.value = it.hobby
            }
        }
    }
}