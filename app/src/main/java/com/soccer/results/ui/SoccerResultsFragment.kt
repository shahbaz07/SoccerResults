package com.soccer.results.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.soccer.results.viewmodel.SoccerResultsViewModel
import com.soccer.results.R
import com.soccer.results.databinding.SoccerResultsFragmentBinding

class SoccerResultsFragment : Fragment() {

    private lateinit var binding: SoccerResultsFragmentBinding

    private lateinit var viewModel: SoccerResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SoccerResultsFragmentBinding.inflate(inflater)
        initViews()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SoccerResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun initViews() {
        binding.message.setOnClickListener {
            findNavController().navigate(R.id.action_navigate_to_detail)
        }
    }
}