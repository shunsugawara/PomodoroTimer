package com.sugaas.pomodoro

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sugaas.pomodoro.MainAdapter.TabContent.*
import com.sugaas.pomodoro.pomodoro.PomodoroListFragment

class MainAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    enum class TabContent {
        POMODORO,
        EXPECTATION_OF_LIFE
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == POMODORO.ordinal) {
            PomodoroListFragment()
        } else {
            ExpectationOfLifeFragment()
        }
    }

    override fun getItemCount(): Int {
        return values().count()
    }
}