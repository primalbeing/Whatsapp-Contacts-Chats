package com.example.whatsapp

import androidx.appcompat.app.AppCompatDelegate
import android.content.res.Configuration
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Force dark mode permanently
    delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES

    setContentView(R.layout.activity_main)

    val phoneInput = findViewById<EditText>(R.id.phoneInput)
    val openWhatsAppBtn = findViewById<Button>(R.id.openWhatsAppBtn)

    openWhatsAppBtn.setOnClickListener {
        val phoneNumber = phoneInput.text.toString().trim()

        if (validateNumber(phoneNumber)) {
            startWhatsAppChat(phoneNumber)
        } else {
            Toast.makeText(this, "Invalid phone number!", Toast.LENGTH_SHORT).show()
        }
    }
}


    private fun validateNumber(number: String): Boolean {
        return number.isNotEmpty() && number.matches(Regex("^[+]?[0-9]{10,15}\$"))
    }

    private fun startWhatsAppChat(phone: String) {
        val uri = Uri.parse("https://wa.me/$phone")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "WhatsApp is not installed!", Toast.LENGTH_SHORT).show()
        }
    }
}
