package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.ukdw.fti.final_71190448.databinding.ActivityAddPendudukBinding

class AddPenduduk : AppCompatActivity() {
    private lateinit var binding : ActivityAddPendudukBinding
    private lateinit var db : DatabaseReference
    lateinit var ImageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPendudukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        binding.btnAdd.setOnClickListener{
            val nik = binding.nik.text.toString()
            val nama = binding.nama.text.toString()
            val ttl = binding.ttl.text.toString()
            val pekerjaan = binding.pekerjaan.text.toString()

            db = FirebaseDatabase.getInstance().getReference("Penduduk")
            val Penduduk = Penduduk(nik,nama,ttl,pekerjaan)
            db.child(nik).setValue(Penduduk).addOnSuccessListener {
                binding.nama.text.clear()
                binding.nik.text.clear()
                binding.ttl.text.clear()
                binding.pekerjaan.text.clear()
                Toast.makeText(this,"Berhasil disimpan", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{

                Toast.makeText(this,"Gagal disimpan",Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}