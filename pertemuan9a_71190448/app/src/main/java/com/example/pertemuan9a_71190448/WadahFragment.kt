package com.example.pertemuan9a_71190448

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WadahFragment : AppCompatActivity(R.layout.wadah_fragment) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.wadah_fragment)

    }
}