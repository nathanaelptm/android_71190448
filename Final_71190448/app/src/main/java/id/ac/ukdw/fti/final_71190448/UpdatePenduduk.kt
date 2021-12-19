package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.ukdw.fti.final_71190448.databinding.ActivityAddPendudukBinding
import id.ac.ukdw.fti.final_71190448.databinding.ActivityUpdatePendudukBinding

class UpdatePenduduk : AppCompatActivity() {
    private lateinit var binding : ActivityUpdatePendudukBinding
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePendudukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nik = intent.getStringExtra("nik")
        val nama = intent.getStringExtra("nama")
        val ttl = intent.getStringExtra("ttl")
        val pekerjaan = intent.getStringExtra("pekerjaan")
        val nikk = findViewById<TextView>(R.id.nik)
        val namaa = findViewById<TextView>(R.id.nama)
        val ttll = findViewById<TextView>(R.id.ttl)
        val pekerjaann = findViewById<TextView>(R.id.pekerjaan)
        nikk.setText(nik.toString())
        namaa.setText(nama.toString())
        ttll.setText(ttl.toString())
        pekerjaann.setText(pekerjaan.toString())

        binding.btnHome.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnUpdate.setOnClickListener {
            val nama = binding.nama.text.toString()
            val nik = binding.nik.text.toString()
            val ttl = binding.ttl.text.toString()
            val pekerjaan = binding.pekerjaan.text.toString()

            updatePenduduk(nik,nama,ttl,pekerjaan)
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnHapus.setOnClickListener {
            var nik = binding.nik.text.toString()
            if(nik.isNotEmpty())
                deletePenduduk(nik)
            else
                Toast.makeText(this, "Masukkan NIK data penduduk yang ingin dihapus!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
    private fun updatePenduduk(nik: String, nama: String, ttl: String, pekerjaan: String) {
        db = FirebaseDatabase.getInstance().getReference("Penduduk")

        val user = mapOf<String,String>(
            "nama" to nama,
            "ttl" to ttl,
            "pekerjaan" to pekerjaan
        )
        db.child(nik).updateChildren(user).addOnSuccessListener {
            binding.nik.text.clear()
            binding.nama.text.clear()
            binding.ttl.text.clear()
            binding.pekerjaan.text.clear()
            Toast.makeText(this,"Berhasil di-Update",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Gagal di-Update",Toast.LENGTH_SHORT).show()
        }}
    private fun deletePenduduk(nik: String){
        db = FirebaseDatabase.getInstance().getReference("Penduduk")
        db.child(nik).removeValue().addOnSuccessListener {
            binding.nik.text.clear()
            Toast.makeText(this, "Berhasil dihapus!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Gagal dihapus!", Toast.LENGTH_SHORT).show()
        }
    }
}