package com.sugaas.pomodoro.pomodoro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sugaas.pomodoro.model.Pomodoro
import com.sugaas.pomodoro.model.PomodoroDao
import com.sugaas.pomodoro.pomodoro.PomodoroListAdapter.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PomodoroListViewModel(
    private val pomodoroDao: PomodoroDao,
) : ViewModel(), PomodoroListDataDelegate {
    override var items: List<Row> = emptyList()
    private val _updateAdapterEvent = MutableSharedFlow<Unit>()
    val updateAdapterEvent: SharedFlow<Unit> = _updateAdapterEvent

    private val _openModalEvent = MutableSharedFlow<Unit>()
    val openModalEvent: SharedFlow<Unit> = _openModalEvent

    override fun onClickRow(row: Row) {
        viewModelScope.launch {
            _openModalEvent.emit(Unit)
        }
    }

    override fun remove(row: Row) {
        viewModelScope.launch(Dispatchers.IO) {
            pomodoroDao.delete(row.item)
            fetch()
        }
    }

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            runBlocking {
                pomodoroDao.insert(Pomodoro(0, "something", 25, 5))
                fetch()
            }
        }
    }

    suspend fun fetch() {
        items = PomodoroListRepository(pomodoroDao)
            .getAll()
            .first()
            .map { Row(it, RowType.ITEM) }

        _updateAdapterEvent.emit(Unit)
    }

}