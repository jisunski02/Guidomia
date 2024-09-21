package dev.jaysonguillen.guidomia.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jaysonguillen.guidomia.R
import dev.jaysonguillen.guidomia.data.model.Cars
import dev.jaysonguillen.guidomia.databinding.CarItemsBinding
import dev.jaysonguillen.guidomia.databinding.MakeItemsBinding

class MakeAdapter: RecyclerView.Adapter<MakeAdapter.MakeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem.model == newItem.model
        }

        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeViewHolder {
        val binding = MakeItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MakeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MakeViewHolder, position: Int) {
        val make = differ.currentList[position]
        holder.bind(make)
    }


    inner class MakeViewHolder(
        val binding: MakeItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cars: Cars) {
            binding.tvMake.text = cars.make
        }
    }




}