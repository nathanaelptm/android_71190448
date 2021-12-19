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
import id.ac.ukdw.fti.final_71190448.databinding.ActivityUpdatePendudukSingleBinding

class UpdatePendudukSingle : AppCompatActivity() {
    private lateinit var binding : ActivityUpdatePendudukSingleBinding
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePendudukSingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnUpdate.setOnClickListener {
            val nama = binding.nama.text.toString()
            val nik = binding.nik.text.toString()
            val ttl = binding.ttl.text.toString()
            val pekerjaan = binding.pekerjaan.text.toString()
            if(nik.isNotEmpty()){
                updatePenduduk(nik,nama,ttl,pekerjaan)
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Harap isi NIK",Toast.LENGTH_SHORT).show()
            }
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
}