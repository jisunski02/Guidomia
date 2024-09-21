package dev.jaysonguillen.guidomia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jaysonguillen.guidomia.databinding.ConsItemsBinding

class ConsAdapter: RecyclerView.Adapter<ConsAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ConsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cons = differ.currentList[position]
        holder.bind(cons)
    }


    inner class ViewHolder(
        val binding: ConsItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cons: String) {
            binding.tvCons.text = cons
        }

    }

}