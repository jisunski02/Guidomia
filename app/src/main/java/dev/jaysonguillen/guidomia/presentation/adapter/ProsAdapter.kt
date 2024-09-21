package dev.jaysonguillen.guidomia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jaysonguillen.guidomia.databinding.ConsItemsBinding
import dev.jaysonguillen.guidomia.databinding.ProsItemsBinding

class ProsAdapter: RecyclerView.Adapter<ProsAdapter.ViewHolder>() {

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
        val binding = ProsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pros = differ.currentList[position]
        holder.bind(pros)
    }


    inner class ViewHolder(
        val binding: ProsItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cons: String) {
            binding.tvPros.text = cons
        }

    }

}