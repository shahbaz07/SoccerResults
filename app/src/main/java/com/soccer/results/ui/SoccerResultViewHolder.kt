package com.soccer.results.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soccer.results.databinding.SoccerResultItemBinding
import com.soccer.results.model.SoccerResult
import java.text.SimpleDateFormat
import java.util.*

class SoccerResultViewHolder(
    private val binding: SoccerResultItemBinding,
    private val listener: SoccerResultItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val dateParseFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.ENGLISH)
    private val dateDisplayFormat = SimpleDateFormat("EEE dd MMMM", Locale.ENGLISH)
    private val timeDisplayFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)

    init {
        binding.root.setOnClickListener {
            listener.onItemClick(it.tag as Int)
        }
    }

    fun bind(result: SoccerResult) {
        binding.root.tag = result.hashCode()
        binding.teamOneName.text = result.teamOneName
        Glide.with(binding.root.context).load(result.teamOneLogo)
            .circleCrop().into(binding.teamOneLogo)
        binding.teamTwoName.text = result.teamTwoName
        Glide.with(binding.root.context).load(result.teamTwoLogo)
            .circleCrop().into(binding.teamTwoLogo)

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