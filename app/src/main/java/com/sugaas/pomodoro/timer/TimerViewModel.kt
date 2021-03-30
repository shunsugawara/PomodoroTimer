package com.sugaas.pomodoro.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sugaas.pomodoro.timer.TimerViewModel.State.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    enum class State {
        WORKING,
        BREAKING
    }

    private val workTime = 25
    private val breakTime = 5

    val currentState = MutableStateFlow(WORKING)
    val timer = MutableStateFlow(25)
    var job: Job? = null

    fun startTimer() {
        job?.cancel()
        job = viewModelScope.launch {
            val time = if (currentState.value == WORKING) workTime else breakTime
            reduceTime(time)

            currentState.value = if (currentState.value == WORKING) BREAKING else WORKING
            startTimer()
        }
    }

    private suspend fun reduceTime(time: Int) {
        timer.value = time
        while (timer.value > 0) {
            delay(1000)
            timer.value--
        }
    }
}