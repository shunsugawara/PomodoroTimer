package com.sugaas.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sugaas.pomodoro.databinding.ActivityMainBinding
import com.sugaas.pomodoro.pomodoro.PomodoroListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val adapter =
            object : FragmentStateAdapter(
                this
            ) {
                override fun createFragment(position: Int): Fragment {
                    return if (position == TabContent.POMODORO.ordinal) {
                        PomodoroListFragment()
                    } else {
                        ExpectationOfLifeFragment()
                    }
                }

                override fun getItemCount(): Int {
                    return TabContent.values().count()
                }
            }
        binding.fragmentsContainer.adapter = adapter


        binding.bottomMenu.setOnNavigationItemSelectedListener { item ->
            val position = if(item.itemId == R.id.pomodoro) 0 else 1
            binding.fragmentsContainer.setCurrentItem(position, false)
            true
        }
    }
}

enum class TabContent() {
    POMODORO,
    EXPECTATION_OF_LIFE
}