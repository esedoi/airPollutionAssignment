package com.paul.airpollutionassignment.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paul.airpollutionassignment.data.Record
import com.paul.airpollutionassignment.databinding.ItemUpBinding


class HorizontalAdapter :
    ListAdapter<Record, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UpHolder(ItemUpBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is UpHolder -> holder.bind(getItem(position))
        }
    }


    class UpHolder(private var binding: ItemUpBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Record) {
            binding.upSiteId.text = item.siteid
            binding.upCounty.text = item.county
            binding.upPmTwoPointFive.text = item.pmTwoPointFive
            binding.upStatus.text = item.status
            binding.upSiteName.text = item.sitename

        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }


    }


}


