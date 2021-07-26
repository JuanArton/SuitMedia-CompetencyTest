package com.juanarton.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.juanarton.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var bindingtmp: ActivityMainBinding? = null
    private val binding get() = bindingtmp!!

    companion object{
        const val IMAGE_PICK_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.nextButton.setOnClickListener {
            val name = binding.nameField.text.toString()
            showDialog(palindromCheck(name), name)
        }
        binding.avatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_REQUEST)
        }
    }

    private fun palindromCheck(nama: String): String {
        val reversed = nama.reversed()
        return if (nama == reversed) {
            "isPalindrome"
        } else {
            "not palindrome"
        }
    }

    private fun showDialog(message: String, name: String){
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok") { dialog, which ->
                val intent = Intent(this, UserActivity::class.java)
                intent.putExtra("nama", name)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
        val dialog = alertDialogBuilder.create()
        dialog.setTitle("Palindrom Check")
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_REQUEST) {
            val imageUri = data?.data
            binding.avatar.setImageURI(imageUri)
        }
    }
}