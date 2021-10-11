package com.example.pertemuan6_71190448

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class FragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_a, container,
        false)

        val btnA = view.findViewById<Button>(R.id.btnA)
        btnA.setOnClickListener {
            val intent = Intent(activity, HalamanDua::class.java)
            activity?.startActivity(intent)
        }
        return view
    }
}