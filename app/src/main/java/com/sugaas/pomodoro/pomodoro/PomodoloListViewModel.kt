package com.sugaas.pomodoro.pomodoro

import androidx.lifecycle.ViewModel

class PomodoloListViewModel : ViewModel(), PomodoroListAdapter.PomodoroListDataSource{

    override val item: List<PomodoroListAdapter.ItemInfo>
        get() = listOf(
            PomodoroListAdapter.ItemInfo("first"),
            PomodoroListAdapter.ItemInfo("second"),
            PomodoroListAdapter.ItemInfo("third")
        )
}