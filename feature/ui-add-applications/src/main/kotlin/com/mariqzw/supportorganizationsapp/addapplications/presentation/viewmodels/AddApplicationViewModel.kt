package com.mariqzw.supportorganizationsapp.addapplications.presentation.viewmodels

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CreateApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddApplicationViewModel(
    private val createApplicationUseCase: CreateApplicationUseCase
) : ViewModel<AddApplicationState, AddApplicationIntent>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun onDateChanged(millis: Long?) {
        _date.value = if (millis != null) dateFormat.format(Date(millis)) else ""
    }

    private val reducer = AddApplicationReducer(AddApplicationState.Init)
    override val state: Flow<AddApplicationState> get() = reducer.state

    private val _date = MutableStateFlow("")
    private val _time = MutableStateFlow("")
    private val _startPoint = MutableStateFlow("")
    private val _endPoint = MutableStateFlow("")
    private val _comment = MutableStateFlow("")

    private val _alertMessage = MutableStateFlow<String?>(null)
    val alertMessage: StateFlow<String?> get() = _alertMessage

    val date: StateFlow<String> get() = _date
    val time: StateFlow<String> get() = _time
    val startPoint: StateFlow<String> get() = _startPoint
    val endPoint: StateFlow<String> get() = _endPoint
    val comment: StateFlow<String> get() = _comment

    fun onDateChanged(value: String) { _date.value = value }
    fun onTimeChanged(value: String) { _time.value = value }
    fun onStartPointChanged(value: String) { _startPoint.value = value }
    fun onEndPointChanged(value: String) { _endPoint.value = value }
    fun onCommentChanged(value: String) { _comment.value = value }

    fun sendApplication() {
        viewModelScope.launch {
            val request = CreateApplicationRequest(
                date = _date.value,
                time = _time.value,
                departureStation = _startPoint.value.trim(),
                destinationStation = _endPoint.value.trim(),
                comment = _comment.value.trim()
            )

            createApplicationUseCase(model = request)
                .onSuccess {
                    reducer.sendIntent(AddApplicationIntent.SendSuccess)
                    clearFields()
                }
                .onFailure {
                    reducer.sendIntent(AddApplicationIntent.SendFailure(it.message ?: "Ошибка отправки заявки"))
                }
        }
    }

    private fun clearFields() {
        _date.value = ""
        _time.value = "00:00"
        _startPoint.value = ""
        _endPoint.value = ""
        _comment.value = ""
    }

    fun dismissAlert() {
        _alertMessage.value = null
    }

    private inner class AddApplicationReducer(initial: AddApplicationState) :
        Reducer<AddApplicationState, AddApplicationIntent>(initial) {

        override fun reduce(oldState: AddApplicationState, intent: AddApplicationIntent) {
            when (intent) {
                AddApplicationIntent.SendSuccess -> {
                    setState(AddApplicationState.Success)
                    _alertMessage.value = "Заявка успешно отправлена"
                }

                is AddApplicationIntent.SendFailure -> {
                    setState(AddApplicationState.Error(intent.error))
                    _alertMessage.value = "Ошибка: ${intent.error}"
                }
            }
        }
    }
}

@Immutable
sealed class AddApplicationIntent : ModelIntent {
    data object SendSuccess : AddApplicationIntent()
    data class SendFailure(val error: String) : AddApplicationIntent()
}

@Immutable
sealed class AddApplicationState : UiState {
    data object Init : AddApplicationState()
    data object Success : AddApplicationState()
    data class Error(val message: String) : AddApplicationState()
}
