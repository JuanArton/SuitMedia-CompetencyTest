package com.juanarton.test.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juanarton.test.databinding.ActivityUserBinding
import com.juanarton.test.ui.event.ListEventActivity
import com.juanarton.test.ui.guest.ListGuestActivity

class UserActivity : AppCompatActivity() {

    private var bindingtmp: ActivityUserBinding? = null
    private val binding get() = bindingtmp!!

    companion object {
        const val ACTIVITY_EVENT_REQUEST_CODE = 0
        const val ACTIVITY_GUEST_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("nama")

        name?.let{
            binding.namaGuest.text = it
        }

        binding.eventButton.setOnClickListener {
            val intent = Intent(this, ListEventActivity::class.java)
            startActivityForResult(intent, ACTIVITY_EVENT_REQUEST_CODE)
        }
        binding.gusetButton.setOnClickListener {
            val intent = Intent(this, ListGuestActivity::class.java)
            startActivityForResult(intent, ACTIVITY_GUEST_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            ACTIVITY_EVENT_REQUEST_CODE ->{
                if (resultCode == Activity.RESULT_OK) {
                    data?.let{
                        val eventName = it.getStringExtra("eventname")
                        binding.eventButton.text = eventName
                    }
                }
            }
            ACTIVITY_GUEST_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let{
                        val guestName = it.getStringExtra("guestname")
                        binding.gusetButton.text = guestName

                        val guestBirthDate = it.getStringExtra("birthdate")
                        guestBirthDate?.let { birthdate ->
                            val date = birthdate.substringAfterLast("-").toInt()
                            if(date % 2 == 0 && date % 3 == 0){
                                Toast.makeText(this, "iOS", Toast.LENGTH_LONG).show()
                            }
                            else if(date % 2 == 0){
                                Toast.makeText(this, "Blackberry", Toast.LENGTH_LONG).show()
                            }
                            else if(date % 3 == 0){
                                Toast.makeText(this, "Android", Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(this, "Feature Phone", Toast.LENGTH_LONG).show()
                            }

                        }
                    }
                }
            }
        }
    }
}