package com.example.pertemuan5_71190448

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HalamanUtama : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_utama)

        val username = intent.getStringExtra("username")

        val textSapaan = findViewById<TextView>(R.id.textSapaan)
        textSapaan.setText("Selamat datang, $username")
    }
}