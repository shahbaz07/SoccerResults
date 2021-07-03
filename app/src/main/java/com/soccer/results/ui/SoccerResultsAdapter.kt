package com.soccer.results.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soccer.results.databinding.SoccerResultItemBinding
import com.soccer.results.model.SoccerResult

class SoccerResultsAdapter(private val listener: SoccerResultItemClickListener) :
    RecyclerView.Adapter<SoccerResultViewHolder>() {

    private val results = mutableListOf<SoccerResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerResultViewHolder {
        val binding =
            SoccerResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SoccerResultViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: SoccerResultViewHolder, position: Int) {
        holder.bind(results[position])
    }

    fun updateData(updatedResults: List<SoccerResult>) {
        results.clear()
        results.addAll(updatedResults)
        notifyDataSetChanged()
    }
}