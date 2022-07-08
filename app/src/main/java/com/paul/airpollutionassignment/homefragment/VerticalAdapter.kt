package com.paul.airpollutionassignment.homefragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paul.airpollutionassignment.R
import com.paul.airpollutionassignment.data.Record
import com.paul.airpollutionassignment.databinding.ItemDownBinding


class VerticalAdapter :
    ListAdapter<Record, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UpHolder(ItemDownBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is UpHolder -> holder.bind(getItem(position))
        }
    }


    class UpHolder(private var binding: ItemDownBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Record) {
            binding.downSiteId.text = item.siteId
            binding.downCounty.text = item.county
            binding.downPmTwoPointFive.text = item.pmTwoPointFive
            binding.downSiteName.text = item.siteName

            if (item.status != itemView.context.getString(R.string.good_status)) {
                binding.imageButton.visibility = View.VISIBLE
                binding.downStatus.text = item.status
                binding.imageButton.setOnClickListener {
                    Toast.makeText(itemView.context,
                        itemView.context.getString(R.string.do_not_go_out, item.status),
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.downStatus.text = itemView.context.getString(R.string.status_is_good)
                binding.imageButton.visibility = View.GONE
            }

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


