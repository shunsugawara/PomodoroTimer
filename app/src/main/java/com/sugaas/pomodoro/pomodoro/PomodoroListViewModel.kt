package com.sugaas.pomodoro.pomodoro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sugaas.pomodoro.model.Pomodoro
import com.sugaas.pomodoro.model.PomodoroDao
import com.sugaas.pomodoro.pomodoro.PomodoroListAdapter.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PomodoroListViewModel(
    private val pomodoroDao: PomodoroDao
) : ViewModel(), PomodoroListDataDelegate {
    override var items: List<ItemInfo> = emptyList()
    val updateAdapterEvent = MutableLiveData<Void>()

    override fun onClickItem(item: ItemInfo) {
        //noop
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
            .map { ItemInfo(it.focusTime, it.breakTime, RowType.ITEM) }

        updateAdapterEvent.postValue(null)
    }


}