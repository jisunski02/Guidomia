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
import dev.jaysonguillen.guidomia.data.utils.CarMake
import dev.jaysonguillen.guidomia.databinding.CarItemsBinding

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

            val carMake = CarMake.fromString(cars.make)
            when (carMake) {
                CarMake.LAND_ROVER -> {
                    binding.ivCar.setImageResource(CarMake.LAND_ROVER.imageResource)
                }
                CarMake.ALPINE -> {
                    binding.ivCar.setImageResource(CarMake.ALPINE.imageResource)
                }
                CarMake.BMW -> {
                    binding.ivCar.setImageResource(CarMake.BMW.imageResource)
                }
                CarMake.MERCEDES_BENZ -> {
                    binding.ivCar.setImageResource(CarMake.MERCEDES_BENZ.imageResource)
                }
                null -> {
                    binding.ivCar.setImageResource(CarMake.MERCEDES_BENZ.imageResource)
                }
            }

            val isExpanded = adapterPosition == expandedPosition
            binding.llProsCons.visibility = if (isExpanded) View.VISIBLE else View.GONE

            itemView.setOnClickListener {
                val oldExpandedPosition = expandedPosition
                expandedPosition = if (isExpanded) RecyclerView.NO_POSITION else adapterPosition

                // Notify changes:
                notifyItemChanged(oldExpandedPosition)
                notifyItemChanged(expandedPosition)
            }
        }
    }

    fun defaultExpandedPositionZero(){
        expandedPosition = 0
        notifyItemChanged(0)
    }

    fun resetExpandedPosition() {
        expandedPosition = RecyclerView.NO_POSITION
        notifyItemChanged(RecyclerView.NO_POSITION)
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