package com.sugaas.pomodoro.pomodoro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugaas.pomodoro.database.DatabaseProvider
import com.sugaas.pomodoro.databinding.FragmentPomodoroListBinding
import com.sugaas.pomodoro.timer.TimerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class PomodoroListFragment : Fragment() {
    @Inject
    lateinit var databaseProvider: DatabaseProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPomodoroListBinding.inflate(inflater, container, false)
        databaseProvider.getPomodoroTable()

        val viewModel = PomodoroListViewModel(databaseProvider.getPomodoroTable())

        binding.pomodoroList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.pomodoroList.adapter = PomodoroListAdapter(viewModel)

        runBlocking {
            viewModel.fetch()
        }

        lifecycleScope.launch {
            viewModel.openModalEvent.collect {
                openModal()
            }
        }

        binding.addButton.setOnClickListener {
            viewModel.add()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.updateAdapterEvent.collect {
                binding.pomodoroList.adapter?.notifyDataSetChanged()
            }
        }


        return binding.root
    }

    private fun openModal() {
        val intent = Intent(context, TimerActivity::class.java)
        startActivity(intent)
    }
}