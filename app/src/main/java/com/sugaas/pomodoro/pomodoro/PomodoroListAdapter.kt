package com.sugaas.pomodoro.pomodoro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugaas.pomodoro.databinding.ItemPomodoroBinding
import com.sugaas.pomodoro.model.Pomodoro

class PomodoroListAdapter(private val sourceAndDelegate: PomodoroListDataDelegate) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface PomodoroListDataDelegate {
        val items: List<Row>
        fun onClickRow(row: Row)
        fun remove(row: Row)
    }

    data class Row(val item: Pomodoro, val type: RowType)
    enum class RowType { ITEM, FOOTER }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPomodoroBinding.inflate(layoutInflater, parent, false)

        return PomodoroItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        sourceAndDelegate.items.getOrNull(position)?.let { item ->
            (holder as? PomodoroItemViewHolder)?.bind(item, sourceAndDelegate)
        }
    }

    override fun getItemCount(): Int {
        return sourceAndDelegate.items.size
    }

    override fun getItemViewType(position: Int): Int {
        return sourceAndDelegate.items.getOrNull(position)?.type?.ordinal ?: RowType.ITEM.ordinal
    }
}