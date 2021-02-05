package com.sugaas.pomodoro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sugaas.pomodoro.model.Pomodoro
import com.sugaas.pomodoro.model.PomodoroDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Database(entities = [Pomodoro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pomodoroDao(): PomodoroDao
}

class DatabaseProvider @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
    private val build = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()

    fun getPomodoroTable(): PomodoroDao {
        return build.pomodoroDao()

    }
}