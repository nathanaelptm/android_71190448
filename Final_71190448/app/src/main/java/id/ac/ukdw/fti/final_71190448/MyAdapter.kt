package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val pendudukList : ArrayList<Penduduk>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val penduduk = pendudukList[position]

        holder.nik.text = penduduk.nik
        holder.nama.text = penduduk.nama
        holder.ttl.text = penduduk.ttl
        holder.pekerjaan.text = penduduk.pekerjaan

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UpdatePenduduk::class.java )
            intent.putExtra("nik",penduduk.nik)
            intent.putExtra("nama",penduduk.nama)
            intent.putExtra("ttl",penduduk.ttl)
            intent.putExtra("pekerjaan",penduduk.pekerjaan)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return pendudukList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nik : TextView = itemView.findViewById(R.id.nik)
        val nama : TextView = itemView.findViewById(R.id.nama)
        val ttl : TextView = itemView.findViewById(R.id.ttl)
        val pekerjaan : TextView = itemView.findViewById(R.id.pekerjaan)

    }
}