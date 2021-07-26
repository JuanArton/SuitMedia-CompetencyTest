package com.juanarton.test.ui.guest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
                val month = data.birthdate.split("-")
                showPrimeDialog(
                    primeNumberCheck(month[1].toInt()),
                    data.nama,
                    data.birthdate
                )
            }
        })
    }

    fun primeNumberCheck(month: Int): String {
        if(month == 1){
            return "Month not prime"
        }
        else{
            for (i in 2..month / 2) {
                if (month % i == 0) {
                    return "Month not prime"
                }
            }
        }
        return "Month is prime"
    }

    private fun showPrimeDialog(message: String, name: String, birthdate: String){
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok") { dialog, which ->
                val intent = Intent().apply {
                    putExtra("guestname", name)
                    putExtra("birthdate", birthdate)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
        val dialog = alertDialogBuilder.create()
        dialog.setTitle("Month Prime Check")
        dialog.show()
    }
}