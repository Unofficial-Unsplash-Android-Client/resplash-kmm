package ru.fursa.unsplash.android.base.eventbus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object LoadEventBus {
    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    suspend fun send(event: Event) {
        _event.emit(event)
    }

    sealed class Event {
        data class Search(val query: String) : Event()
        object CloseSearch : Event()
        object LoadData : Event()
    }
}
