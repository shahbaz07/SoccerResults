package com.soccer.results.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soccer.results.viewmodel.SoccerResultsViewModel
import com.soccer.results.databinding.SoccerResultDetailFragmentBinding

class SoccerResultDetailFragment : Fragment() {

    private lateinit var binding: SoccerResultDetailFragmentBinding

    private lateinit var viewModel: SoccerResultsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SoccerResultDetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SoccerResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}