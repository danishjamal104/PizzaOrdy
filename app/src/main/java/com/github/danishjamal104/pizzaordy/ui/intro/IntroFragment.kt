package com.github.danishjamal104.pizzaordy.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.danishjamal104.pizzaordy.R
import com.github.danishjamal104.pizzaordy.databinding.FragmentHomeBinding
import com.github.danishjamal104.pizzaordy.databinding.FragmentIntroBinding


class IntroFragment : Fragment(R.layout.fragment_intro) {

    private lateinit var _binding: FragmentIntroBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIntroBinding.bind(view)

        binding.orderNow.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_homeFragment)
        }
    }
}