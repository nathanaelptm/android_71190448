package com.example.pertemuan6_71190448


import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class HalamanDua : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_dua)
        fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            setContentView(R.layout.activity_halaman_dua)

        }
    }
}