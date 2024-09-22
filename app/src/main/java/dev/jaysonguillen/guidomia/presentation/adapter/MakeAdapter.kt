package dev.jaysonguillen.guidomia.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jaysonguillen.guidomia.databinding.MakeItemsBinding

class MakeAdapter: RecyclerView.Adapter<MakeAdapter.MakeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
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
        private val binding: MakeItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(make: String) {
            binding.tvMake.text = make

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(make)
                }
            }
        }
    }

    private var onItemClickListener: ((String)->Unit)?=null

    fun setOnItemClickLister(listener: ((String)->Unit)){
        onItemClickListener = listener
    }

}