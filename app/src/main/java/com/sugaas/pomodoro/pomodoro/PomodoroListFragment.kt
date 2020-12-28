package com.sugaas.pomodoro.pomodoro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugaas.pomodoro.databinding.FragmentPomodoroListBinding

class PomodoroListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPomodoroListBinding.inflate(inflater, container, false)

//        binding.toOtherFragmentButton.setOnClickListener {
//            val arg = Bundle().apply { putString("foo", "passedValue") }
//            findNavController().navigate(R.id.action_pomodolo_to_expectation, arg)
//        }

        val viewModel = PomodoroListViewModel()

        binding.pomodoroList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val value = PomodoroListAdapter(viewModel)
        binding.pomodoroList.adapter = value

        return binding.root
    }
}

