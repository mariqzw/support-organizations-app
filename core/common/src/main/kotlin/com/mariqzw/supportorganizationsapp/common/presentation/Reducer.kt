package com.mariqzw.supportorganizationsapp.common.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Reducer<S : UiState, I : ModelIntent>(initialVal: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S> get() = _state.asStateFlow()

    fun sendIntent(intent: I) {
        reduce(_state.value, intent)
    }

    fun setState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, intent: I)
}
