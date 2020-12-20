package com.sugaas.pomodoro.pomodoro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding

class PomodoroListAdapter(val dataSource: PomodoroListDataSource) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface PomodoroListDataSource {
        val item: List<ItemInfo>
    }

    data class ItemInfo(val name: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPomodoroBinding.inflate(layoutInflater, parent, false)

        return PomodoroItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSource.item.getOrNull(position)?.let { item ->
            (holder as? PomodoroItemViewHolder)?.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }


}