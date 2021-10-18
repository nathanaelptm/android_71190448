package com.example.pertemuan7_71190448

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class KontakAdapter (var listKontak: ArrayList<Kontak>, var context: Context):
    RecyclerView.Adapter<KontakAdapter.KontakHolder>() {
    class KontakHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(kontak: Kontak, context:Context){
            view.findViewById<ImageView>(R.id.profilePicture).setImageResource(kontak.profilePicture)
            view.findViewById<TextView>(R.id.personName).setText(kontak.personName)
            view.findViewById<TextView>(R.id.nomorHP).setText(kontak.nomorHP)

            view.setOnClickListener {
                Toast.makeText(context,"${kontak.personName}: ${kontak.nomorHP}",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return KontakHolder(v)
    }

    override fun onBindViewHolder(holder: KontakHolder, position: Int) {
        holder.bind(listKontak[position], context)
    }

    override fun getItemCount(): Int {
        return listKontak.size
    }

}
