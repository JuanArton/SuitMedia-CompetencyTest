package com.juanarton.test.ui.guest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.juanarton.test.adapter.ListGuestAdapter
import com.juanarton.test.data.GuestDataClass
import com.juanarton.test.databinding.ActivityListGuestBinding
import com.juanarton.test.model.ViewModelFactory

class ListGuestActivity : AppCompatActivity() {

    private var bindingtmp: ActivityListGuestBinding? = null
    private val binding get() = bindingtmp!!
    private lateinit var viewModel: ListGuestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityListGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(ListGuestViewModel::class.java)

        viewModel.listGuest.observe(this){ guestList ->
            setRecyclerView(guestList)
        }
    }

    private fun setRecyclerView(guestList: List<GuestDataClass>){
        binding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        val adapter = ListGuestAdapter(guestList)
        binding.progressBar.visibility = View.INVISIBLE
        binding.rvGuest.adapter = adapter
        adapter.setOnItemClickCallback(object : ListGuestAdapter.OnItemClickCallback{
            override fun onItemClicked(data: GuestDataClass) {
                val intent = Intent().apply {
                    putExtra("guestname", data.nama)
                    putExtra("birthdate", data.birthdate)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }
}