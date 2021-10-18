package com.example.pertemuan7_71190448

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listKontak = arrayListOf<Kontak>()
        listKontak.add(Kontak("Roy Ki Yossi","0823 1928 9393",R.mipmap.siyos))
        listKontak.add(Kontak("Suhu Daniel","0882 8672 8569",R.mipmap.dnlkus))

        val recKontak = findViewById<RecyclerView>(R.id.recKontak)
        recKontak.layoutManager = LinearLayoutManager(this)
        val adapter = KontakAdapter(listKontak, this)
        recKontak.adapter = adapter
    }
}