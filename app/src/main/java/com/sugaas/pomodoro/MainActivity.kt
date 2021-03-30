package com.sugaas.pomodoro

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.sugaas.pomodoro.MainAdapter.TabContent.EXPECTATION_OF_LIFE
import com.sugaas.pomodoro.MainAdapter.TabContent.POMODORO
import com.sugaas.pomodoro.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.fragmentsContainer.adapter = MainAdapter(this)

        binding.bottomMenu.setOnNavigationItemSelectedListener { item ->
            val position =
                if (item.itemId == R.id.pomodoro) POMODORO.ordinal else EXPECTATION_OF_LIFE.ordinal

            binding.fragmentsContainer.setCurrentItem(position, false)
            true
        }
    }
}

