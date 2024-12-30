package org.sopt.and.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.presentation.utils.UiEvent
import org.sopt.and.presentation.utils.UiSideEffect
import org.sopt.and.presentation.utils.UiState

abstract class BaseViewModel<State: UiState, SideEffect: UiSideEffect, Event: UiEvent> : ViewModel() {

    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState : StateFlow<State>
        get() = _uiState.asStateFlow()
    val currentState: State
        get() = uiState.value

    // 이벤트 처리할 필요 없으면 무시됨 (구독자 없으면)
    private val _uiEvent : MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event>
        get() = _uiEvent.asSharedFlow()

    // 각각의 이벤트가 오직 하나의 구독자에게만 전달됨 (HotStream 임)
    private val _sideEffect : MutableSharedFlow<SideEffect> = MutableSharedFlow()
    val sideEffect: Flow<SideEffect>
        get() = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiEvent.collect { event ->
                dispatchEvent(event)
            }
        }
    }

    // Set new Ui State
    protected fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }

     // Set new Event
    open fun setEvent(newEvent: Event) {
        viewModelScope.launch { _uiEvent.emit(newEvent) }
    }

    // Set new SideEffect
    protected fun setSideEffect(newSideEffect: SideEffect) {
        viewModelScope.launch { _sideEffect.emit(newSideEffect) }
    }

    // Start listening to Event
    private fun dispatchEvent(event: Event) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    protected abstract fun handleEvent(event : Event)

}