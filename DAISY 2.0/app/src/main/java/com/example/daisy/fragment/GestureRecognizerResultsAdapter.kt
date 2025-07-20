//Updated GestureRecognizerResultsAdapter

package com.example.daisy.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daisy.databinding.ItemGestureRecognizerResultBinding
import com.google.mediapipe.tasks.components.containers.Category
import java.util.Locale
import kotlin.math.min

class GestureRecognizerResultsAdapter :
    RecyclerView.Adapter<GestureRecognizerResultsAdapter.ViewHolder>() {
    companion object {
        private const val NO_VALUE = "Place your hand in front of the camera"
    }

    private var adapterCategories: MutableList<Category?> = mutableListOf()
    private var adapterSize: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun updateResults(categories: List<Category>?) {
        if (categories.isNullOrEmpty()) {
            // Ensure the list only contains a single placeholder message
            adapterCategories = mutableListOf(null)
        } else {
            val sortedCategories = categories.sortedByDescending { it.score() }
            val minSize = min(sortedCategories.size, adapterSize)
            adapterCategories = sortedCategories.take(minSize).toMutableList()
        }
        notifyDataSetChanged()
    }


    fun updateAdapterSize(size: Int) {
        adapterSize = size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemGestureRecognizerResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = adapterCategories.getOrNull(position)
        holder.bind(category?.categoryName(), category?.score())
    }

    override fun getItemCount(): Int = maxOf(adapterCategories.size, 1)

inner class ViewHolder(private val binding: ItemGestureRecognizerResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(label: String?, score: Float?) {
        with(binding) {
            tvScore.text = if (score != null) String.format(
                Locale.US,
                "%.2f",
                score
            ) else NO_VALUE
        }
    }
}
}



