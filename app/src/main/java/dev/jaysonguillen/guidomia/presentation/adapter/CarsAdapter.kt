package dev.jaysonguillen.guidomia.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jaysonguillen.guidomia.R
import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.databinding.CarItemsBinding
import kotlin.coroutines.coroutineContext

class CarsAdapter(
    private val context: Context,
    private val consAdapter: ConsAdapter,
    private val prosAdapter: ProsAdapter): RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {

    // Variable to track the position of the currently expanded item
    private var expandedPosition: Int = 0

    private val callback = object : DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem.model == newItem.model
        }

        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val cars = differ.currentList[position]
        holder.bind(cars)
    }


    inner class CarsViewHolder(
        val binding: CarItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cars: Cars) {

            consAdapter.differ.submitList(cars.consList)


            binding.rvCons.apply {
                adapter = consAdapter
                layoutManager = LinearLayoutManager(context)

            }

            prosAdapter.differ.submitList(cars.prosList)

            binding.rvPros.apply {
                adapter = prosAdapter
                layoutManager = LinearLayoutManager(context)

            }

            addStars(binding.llStarContainer, cars.rating)

            binding.tvPrice.text =  "Price : ${convertToK(cars.marketPrice)}"
            binding.tvModelMake.text = "${cars.make} ${cars.model}"

            when (cars.make) {
                "Land Rover" -> {
                    binding.ivCar.setImageResource(R.drawable.range_rover)
                }
                "Alpine" -> {
                    binding.ivCar.setImageResource(R.drawable.alpine_roadster)
                }
                "BMW" -> {
                    binding.ivCar.setImageResource(R.drawable.bmw_330i)
                }
                "Mercedez Benz" -> {
                    binding.ivCar.setImageResource(R.drawable.mercedez_benz_glc)
                }
                else -> {

                }
            }

// Show or hide the expandable layout based on whether this is the currently expanded item
            val isExpanded = adapterPosition == expandedPosition
            binding.llProsCons.visibility = if (isExpanded) View.VISIBLE else View.GONE

            // Set up click listener to toggle the expanded/collapsed state
            itemView.setOnClickListener {
                val oldExpandedPosition = expandedPosition

                // Update the expanded position to the new item or collapse if clicked again
                expandedPosition = if (isExpanded) RecyclerView.NO_POSITION else position

                // Notify changes:
                // 1. Collapse the old item (if there was one expanded)
                // 2. Expand the new item
                notifyItemChanged(oldExpandedPosition)
                notifyItemChanged(expandedPosition)
            }
        }
    }



    private fun convertToK(value: Double): String {
        return if (value >= 1000) {
            "${(value / 1000).toInt()}K"
        } else {
            value.toString()
        }
    }


    private fun addStars(container: LinearLayoutCompat, rating: Int) {
        container.removeAllViews() // Clear previous stars if any

        for (i in 1..rating) {
            val starImageView = ImageView(context)
            starImageView.setImageResource(R.drawable.ic_star_rate) // Use your star image here
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            starImageView.layoutParams = params
            container.addView(starImageView)
        }
    }

}