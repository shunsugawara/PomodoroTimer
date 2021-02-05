package com.sugaas.pomodoro.pomodoro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugaas.pomodoro.database.DatabaseProvider
import com.sugaas.pomodoro.databinding.FragmentPomodoroListBinding
import dagger.hilt.android.AndroidEntryPoint
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

//        binding.toOtherFragmentButton.setOnClickListener {
//            val arg = Bundle().apply { putString("foo", "passedValue") }
//            findNavController().navigate(R.id.action_pomodolo_to_expectation, arg)
//        }

        val viewModel = PomodoroListViewModel(databaseProvider.getPomodoroTable())

        binding.pomodoroList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.pomodoroList.adapter = PomodoroListAdapter(viewModel)

        runBlocking {
            viewModel.fetch()
        }


        binding.addButton.setOnClickListener {
            viewModel.add()
        }

        viewModel.updateAdapterEvent.observe(viewLifecycleOwner, Observer {
            binding.pomodoroList.adapter?.notifyDataSetChanged()
        })


        return binding.root
    }
}

