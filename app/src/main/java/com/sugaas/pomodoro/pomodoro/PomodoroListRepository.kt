package com.sugaas.pomodoro.pomodoro

import com.sugaas.pomodoro.model.Pomodoro
import com.sugaas.pomodoro.model.PomodoroDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PomodoroListRepository constructor(
    private val pomodoroDao: PomodoroDao
) {
    fun getAll(): Flow<List<Pomodoro>> = pomodoroDao.getAll()
}