package com.soccer.results.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soccer.results.viewmodel.SoccerResultsViewModel
import com.soccer.results.databinding.SoccerResultDetailFragmentBinding
import com.soccer.results.model.SoccerResult
import com.soccer.results.model.SoccerResultState
import com.soccer.results.viewmodel.SoccerResultDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SoccerResultDetailFragment : Fragment() {

    private lateinit var binding: SoccerResultDetailFragmentBinding

    private val viewModel: SoccerResultDetailViewModel by viewModels()

    private val dateParseFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.ENGLISH)
    private val dateDisplayFormat = SimpleDateFormat("EEE dd MMMM", Locale.ENGLISH)
    private val timeDisplayFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SoccerResultDetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.soccerResult.collect { soccerResults ->
                    when (soccerResults) {
                        is SoccerResultState.Success -> updateData(soccerResults.results)
                        is SoccerResultState.Error -> Timber.e("Error")
                    }
                }
            }
        }
        val uid = arguments?.getInt("uid")
        uid?.let {
            viewModel.fetchSoccerResults(it)
        }
    }

    private fun updateData(result: SoccerResult) {
        binding.teamOneName.text = result.teamOneName
        Glide.with(binding.root.context).load(result.teamOneLogo)
            .apply(
                RequestOptions().override(64, 64).circleCrop()
                /*.placeholder(R.drawable.placehoder)*/
            ).into(binding.teamOneLogo)
        binding.teamTwoName.text = result.teamTwoName
        Glide.with(binding.root.context).load(result.teamTwoLogo)
            .apply(
                RequestOptions().override(64, 64).circleCrop()
                /*.placeholder(R.drawable.placehoder)*/
            ).into(binding.teamTwoLogo)

        binding.score.text = result.score
        val date = dateParseFormat.parse(result.dateTime)
        date?.let {
            binding.date.text = dateDisplayFormat.format(it)
            binding.time.text = timeDisplayFormat.format(it)
        }?: kotlin.run {
            binding.date.text = ""
            binding.time.text = ""
        }
    }
}