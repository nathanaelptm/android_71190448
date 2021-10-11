package com.example.pertemuan6_71190448

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view: View = inflater.inflate(R.layout.fragment_b, container,
        false)
        val btnB = view.findViewById<Button>(R.id.btnB)
        btnB.setOnClickListener {
            val intent = Intent(activity, HalamanTiga::class.java )
            activity?.startActivity(intent)
        }
        return view
    }
}