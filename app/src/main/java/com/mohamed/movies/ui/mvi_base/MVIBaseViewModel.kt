package com.mohamed.movies.ui.mvi_base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MVIBaseViewModel
<UI_STATE : UIState, EVENT : Event, ONE_TIME_ACTION : OneTimeAction> : ViewModel() {

    protected abstract fun createInitialState(): UI_STATE
    protected abstract fun onEvent(event: EVENT)

    private val _state = MutableStateFlow(this.createInitialState())
    val state: StateFlow<UI_STATE> = _state

    private val _event = MutableSharedFlow<EVENT>(replay = 1)
    val event: SharedFlow<EVENT> = _event

    private val _oneTimeAction = Channel<ONE_TIME_ACTION>()
    val action = _oneTimeAction.receiveAsFlow()

    val currentState: UI_STATE
        get() = _state.value

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect { event ->
                onEvent(event)
            }
        }
    }

    init {
        subscribeToEvents()
    }

    fun sendOneTimeAction(action: ONE_TIME_ACTION) {
        viewModelScope.launch {
            _oneTimeAction.send(action)
        }
    }

    fun sendEvent(event: EVENT) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    protected fun setState(reducer: UI_STATE.() -> UI_STATE) {
        _state.value = currentState.reducer()
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}