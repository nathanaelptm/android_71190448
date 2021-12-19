package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.ukdw.fti.final_71190448.databinding.ActivityDeletePendudukBinding

class DeletePenduduk : AppCompatActivity() {
    private lateinit var binding : ActivityDeletePendudukBinding
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletePendudukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnDelete.setOnClickListener {
            var nik = binding.etNik.text.toString()
            if(nik.isNotEmpty())
                deletePenduduk(nik)
            else
                Toast.makeText(this, "Masukkan NIK data penduduk yang ingin dihapus!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deletePenduduk(nik: String){
        db = FirebaseDatabase.getInstance().getReference("Penduduk")
        db.child(nik).removeValue().addOnSuccessListener {
            binding.etNik.text.clear()
            Toast.makeText(this, "Berhasil dihapus!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Gagal dihapus!", Toast.LENGTH_SHORT).show()
        }
    }
}