package com.sugaas.pomodoro.pomodoro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding

class PomodoroListAdapter(private val dataDelegate: PomodoroListDataDelegate) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface PomodoroListDataDelegate {
        val item: List<ItemInfo>
        fun onClickItem(item: ItemInfo)
    }

    data class ItemInfo(val doingTime: Int, val restTime:Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPomodoroBinding.inflate(layoutInflater, parent, false)

        return PomodoroItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataDelegate.item.getOrNull(position)?.let { item ->
            (holder as? PomodoroItemViewHolder)?.bind(item, dataDelegate)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }


}