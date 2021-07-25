package com.juanarton.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanarton.test.R
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.data.GuestDataClass
import com.juanarton.test.databinding.ItemEventBinding
import com.juanarton.test.databinding.ItemGuestBinding

class ListGuestAdapter (private val listData: List<GuestDataClass>) :
    RecyclerView.Adapter<ListGuestAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_guest, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, idx: Int) {
        val guestData = listData[idx]
        holder.bindData(guestData)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(guestData) }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemGuestBinding.bind(itemView)
        fun bindData(data: GuestDataClass){
            with(itemView){
                binding.apply {
                    itemGuestNama.text = data.nama
                    itemGuestBirthdate.text = data.birthdate
                    Glide.with(context)
                        .load(data.image)
                        .centerCrop()
                        .into(itemGuestImage)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GuestDataClass)
    }
}