package com.soccer.results.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.soccer.results.viewmodel.SoccerResultsViewModel
import com.soccer.results.R
import com.soccer.results.databinding.SoccerResultsFragmentBinding
import com.soccer.results.model.SoccerResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SoccerResultsFragment : Fragment() {

    private lateinit var binding: SoccerResultsFragmentBinding

    private val viewModel: SoccerResultsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SoccerResultsFragmentBinding.inflate(inflater)
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                /*viewModel.soccerResults.collect { soccerResults ->
                    when (soccerResults) {
                        is SoccerResultState.Success -> Timber.d("Success")
                        is SoccerResultState.Error -> Timber.e("Error")
                    }
                }*/
            }
        }
        viewModel.fetchSoccerResults()
    }

    /*private fun showError(exception: Any): Any {

    }

    private fun showFavoriteNews(news: Any): Any {

    }*/

    private fun initViews() {
        binding.message.setOnClickListener {
            findNavController().navigate(R.id.action_navigate_to_detail)
        }
    }
}