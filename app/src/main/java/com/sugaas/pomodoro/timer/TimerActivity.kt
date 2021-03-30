package com.sugaas.pomodoro.timer

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sugaas.pomodoro.databinding.ActivityTimerBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding
    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(LayoutInflater.from(this))
        viewModel = TimerViewModel()

        binding.closeButton.setOnClickListener { finish() }
        viewModel.startTimer()

        observeStateFlows()

        setContentView(binding.root)
    }

    private fun observeStateFlows() {
        lifecycleScope.launch {
            viewModel.timer.collect {
                binding.remainTimeText.text = it.toString()
            }
        }
        lifecycleScope.launch {
            viewModel.currentState.collect {
                binding.currentStateText.text = it.toString()
            }
        }
    }
}