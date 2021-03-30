package com.sugaas.pomodoro.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Entity
data class Pomodoro(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "work_time") val workTime: Int,
    @ColumnInfo(name = "break_time") val breakTime: Int
)

@Dao
interface PomodoroDao {
    @Query("SELECT * FROM pomodoro")
    fun getAll(): Flow<List<Pomodoro>>

    @Insert
    fun insert(pomodoro: Pomodoro)

    @Delete
    fun delete(pomodoro: Pomodoro)
}

