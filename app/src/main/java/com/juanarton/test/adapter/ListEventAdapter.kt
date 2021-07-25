package com.juanarton.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanarton.test.R
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.databinding.ItemEventBinding

class ListEventAdapter(private val listData: ArrayList<EventDataClass>) :
    RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, idx: Int) {
        val eventData = listData[idx]
        holder.bindData(eventData)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(eventData) }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemEventBinding.bind(itemView)
        fun bindData(data: EventDataClass){
            with(itemView){
                binding.apply {
                    itemNama.text = data.nama
                    itemTanggal.text = data.tanggal
                    val image = context.resources.getIdentifier(data.image, "drawable", context.packageName)
                    Glide.with(context)
                        .load(image)
                        .centerCrop()
                        .into(itemEventImage)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: EventDataClass)
    }
}