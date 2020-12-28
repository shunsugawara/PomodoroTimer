package com.sugaas.pomodoro.pomodoro

import androidx.lifecycle.ViewModel
import com.sugaas.pomodoro.pomodoro.PomodoroListAdapter.*

class PomodoroListViewModel : ViewModel(), PomodoroListDataDelegate{

    override val item: List<ItemInfo>
        get() = listOf(
            ItemInfo(10,3),
            ItemInfo(10,3),
            ItemInfo(10,3)
        )

    override fun onClickItem(item: ItemInfo) {
        println("foo - ${item.doingTime.toString()}")
    }

}