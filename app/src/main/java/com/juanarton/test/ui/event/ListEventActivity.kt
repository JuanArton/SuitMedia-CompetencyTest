package com.juanarton.test.ui.event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanarton.test.adapter.ListEventAdapter
import com.juanarton.test.data.DataDummy
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.databinding.ActivityListEventBinding
import com.juanarton.test.model.ViewModelFactory

class ListEventActivity : AppCompatActivity() {

    private var bindingtmp: ActivityListEventBinding? = null
    private val binding get() = bindingtmp!!
    private lateinit var viewModel: ListEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityListEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(ListEventViewModel::class.java)

        val eventList = viewModel.getEventDummyData()
        setRecyclerView(eventList)
    }

    private fun setRecyclerView(eventList: ArrayList<EventDataClass>){
        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        val adapter = ListEventAdapter(eventList)
        binding.rvEvent.adapter = adapter
        adapter.setOnItemClickCallback(object : ListEventAdapter.OnItemClickCallback{
            override fun onItemClicked(data: EventDataClass) {
                val intent = Intent().apply {
                    putExtra("eventname", data.nama)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }
}