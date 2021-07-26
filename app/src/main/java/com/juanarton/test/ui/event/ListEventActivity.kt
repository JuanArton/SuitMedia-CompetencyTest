package com.juanarton.test.ui.event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanarton.test.adapter.ListEventAdapter
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.databinding.ActivityListEventBinding
import com.juanarton.test.model.ViewModelFactory
import com.juanarton.test.ui.maps.MapsFragment

class ListEventActivity : AppCompatActivity() {

    private var bindingtmp: ActivityListEventBinding? = null
    private val binding get() = bindingtmp!!
    private lateinit var viewModel: ListEventViewModel
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityListEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(ListEventViewModel::class.java)

        val eventList = viewModel.getEventDummyData()
        setRecyclerView(eventList)

        binding.plusButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, MapsFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.backButton.setOnClickListener {
            finish()
        }
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
        binding.refreshView.setOnRefreshListener {
            val newList = viewModel.getEventDummyData()
            val newAdapter = ListEventAdapter(newList)
            binding.rvEvent.adapter = newAdapter
            binding.refreshView.isRefreshing = false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(count == 0){
            supportFragmentManager.popBackStack()
            count = 1
        }
        else{
            finish()
        }
    }
}