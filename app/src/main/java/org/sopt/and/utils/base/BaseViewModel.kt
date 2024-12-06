package org.sopt.and.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.and.presentation.utils.UiEffect
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiState

abstract class BaseViewModel<uiEvent : UiEvent, uiState : UiState, uiEffect : UiEffect> : ViewModel() {

    private val initialState : uiState by lazy { createInitialState() }
    abstract fun createInitialState() : uiState

    val currentState: uiState
        get() = uiState.value

    private val _uiState : MutableStateFlow<uiState> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow() // 이벤트 처리할 필요 없으면 무시됨 (구독자 없으면)
    val event = _uiEvent.asSharedFlow()

    private val _uiEffect : Channel<UiEffect> = Channel() // 각각의 이벤트가 오직 하나의 구독자에게만 전달됨 (HotStream 임)
    val effect = _uiEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

     // Set new Event
    fun setEvent(newEvent : UiEvent) {
        viewModelScope.launch { _uiEvent.emit(newEvent) }
    }

    // Set new Ui State
    protected fun setState(newState: uiState) {
        _uiState.value = newState
    }

    // Set new Effect
    protected fun setEffect(newEffect: UiEffect) {
        viewModelScope.launch { _uiEffect.send(newEffect) }
    }

     // Start listening to Event
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event : UiEvent)
    abstract fun handleEffect(effect: UiEffect)
}