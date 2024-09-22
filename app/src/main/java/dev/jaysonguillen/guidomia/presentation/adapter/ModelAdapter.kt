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
import dev.jaysonguillen.guidomia.databinding.ModelItemsBinding

class ModelAdapter: RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = ModelItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val model = differ.currentList[position]
        holder.bind(model)
    }


    inner class ModelViewHolder(
        private val binding: ModelItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: String) {
            binding.tvModel.text = model

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(model)
                }
            }
        }
    }

    private var onItemClickListener: ((String)->Unit)?=null

    fun setOnItemClickLister(listener: ((String)->Unit)){
        onItemClickListener = listener
    }

}