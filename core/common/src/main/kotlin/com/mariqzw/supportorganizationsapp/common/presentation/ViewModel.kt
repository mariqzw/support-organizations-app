package com.mariqzw.supportorganizationsapp.common.presentation

import kotlinx.coroutines.flow.Flow

abstract class ViewModel<T : UiState, in I : ModelIntent> {
    abstract val state: Flow<T>
}
