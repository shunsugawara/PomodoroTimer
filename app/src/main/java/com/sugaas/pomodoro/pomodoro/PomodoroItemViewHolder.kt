package com.sugaas.pomodoro.pomodoro

import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.R
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding

class PomodoroItemViewHolder(private val binding: ItemPomodoroBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: PomodoroListAdapter.ItemInfo,
        dataDelegate: PomodoroListAdapter.PomodoroListDataDelegate
    ) {
        binding.delegate = dataDelegate
        binding.item = item
        val timeStr = binding.root.context.getString(R.string.doing_time_text, item.doingTime)
        binding.doingTimeText.text = timeStr
        val breakTimeStr = binding.root.context.getString(R.string.break_time_text, item.breakTime)
        binding.breakTimeText.text = breakTimeStr


    }
}