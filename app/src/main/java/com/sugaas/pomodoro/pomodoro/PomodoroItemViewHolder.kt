package com.sugaas.pomodoro.pomodoro

import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding

class PomodoroItemViewHolder(private val binding: ItemPomodoroBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PomodoroListAdapter.ItemInfo) {
        binding.pomodoroItemName.text = item.name
    }
}