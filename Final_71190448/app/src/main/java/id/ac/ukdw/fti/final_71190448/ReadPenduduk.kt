package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.ukdw.fti.final_71190448.databinding.ActivityReadPendudukBinding

class ReadPenduduk : AppCompatActivity() {
    private lateinit var binding : ActivityReadPendudukBinding
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadPendudukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnSearch.setOnClickListener {
            val nik : String = binding.nik.text.toString()
            if (nik.isNotEmpty()){
                searchPenduduk(nik)
            }else{
                Toast.makeText(this,"Masukkan NIK yang ingin dicari!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun searchPenduduk(userName: String) {

        db = FirebaseDatabase.getInstance().getReference("Penduduk")
        db.child(userName).get().addOnSuccessListener {

            if (it.exists()){
                val nama = it.child("nama").value
                val ttl = it.child("ttl").value
                val pekerjaan = it.child("pekerjaan").value
                Toast.makeText(this,"Pencarian sukses!",Toast.LENGTH_SHORT).show()
                binding.nik.text.clear()
                binding.tvNama.text = nama.toString()
                binding.tvTtl.text = ttl.toString()
                binding.tvPekerjaan.text = pekerjaan.toString()
            }else{
                Toast.makeText(this,"Tidak ada data penduduk ditemukan!",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Gagal mencari data penduduk",Toast.LENGTH_SHORT).show()
        }
    }
}