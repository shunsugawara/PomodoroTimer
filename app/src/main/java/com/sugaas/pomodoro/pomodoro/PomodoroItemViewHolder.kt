package com.sugaas.pomodoro.pomodoro

import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.R
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding

class PomodoroItemViewHolder(private val binding: ItemPomodoroBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: PomodoroListAdapter.Row,
        delegate: PomodoroListAdapter.PomodoroListDataDelegate
    ) {
        binding.contentNameText.text = item.item.title
        val timeStr = binding.root.context.getString(R.string.work_time_text, item.item.workTime)
        binding.workTimeText.text = timeStr
        val breakTimeStr =
            binding.root.context.getString(R.string.break_time_text, item.item.breakTime)
        binding.breakTimeText.text = breakTimeStr

        binding.root.setOnClickListener {
            delegate.onClickRow(item)
        }
        binding.removeButton.setOnClickListener {
            delegate.remove(item)
        }

    }
}