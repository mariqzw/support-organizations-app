package com.mariqzw.supportorganizationsapp.common.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class ViewModel<T : UiState, in I : ModelIntent> : ViewModel() {
    abstract val state: Flow<T>
}
