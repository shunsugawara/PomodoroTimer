package com.sugaas.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sugaas.pomodoro.databinding.FragmentExpectationOfLifeBinding

class ExpectationOfLifeFragment : Fragment() {

    private val liveData: MutableLiveData<String> = MutableLiveData("aiueo")

    var i = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentExpectationOfLifeBinding.inflate(inflater,container, false)

        liveData.observe(viewLifecycleOwner, Observer { binding.title.text = it})

        arguments?.getString("foo")?.let {
            liveData.value = it
        }

        binding.changeTextButton.setOnClickListener {
            i++
            liveData.value = i.toString()
        }

        return binding.root
    }
}