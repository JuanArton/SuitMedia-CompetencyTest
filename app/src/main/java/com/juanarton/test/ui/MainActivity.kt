package com.juanarton.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juanarton.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var bindingtmp: ActivityMainBinding? = null
    private val binding get() = bindingtmp!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val name = binding.nameField.text.toString()
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("nama", name)
            startActivity(intent)
        }
    }
}