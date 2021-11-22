package com.example.pertemuan11_71190448

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edtId = findViewById<TextView>(R.id.edtId)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val namaMahasiswa = findViewById<EditText>(R.id.namaMahasiswa)
        firestore = FirebaseFirestore.getInstance()

        saveButton.setOnClickListener {
            val namaMahasiswa = findViewById<EditText>(R.id.namaMahasiswa).text.toString()
            val nim = findViewById<EditText>(R.id.nim).text.toString()
            val ipk = findViewById<EditText>(R.id.ipk).text.toString()
            saveFireStore(namaMahasiswa, nim, ipk)
        }
        readFireStoreData()

        searchButton.setOnClickListener{
            textViewResult.setText("")
            firestore?.collection("users").whereEqualTo("namaMahasiswa", namaMahasiswa.text.toString()).get()!!
                .addOnSuccessListener { docs ->
                    val result: StringBuffer = StringBuffer()
                    for(doc in docs){
                        result.append(doc.data.getValue("namaMahasiswa")).append(" ")
                            .append(doc.data.getValue("nim")).append("\n\n")
                            .append(doc.data.getValue("ipk")).append("\n\n")
                    }
                        textViewResult.setText(result)

            }.addOnFailureListener {
                Log.d(TAG, "Pengambilan dokumen gagal")
                }
        }



    }

    fun saveFireStore(namaMahasiswa: String, nim: String, ipk: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["namaMahasiswa"] = namaMahasiswa
        user["nim"] = nim
        user["ipk"] = ipk

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Proses penyimpanan sukses ", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Proses penyimpanan gagal ", Toast.LENGTH_SHORT)
                    .show()
            }
        readFireStoreData()
    }

    fun readFireStoreData() {
        val db = FirebaseFirestore.getInstance()
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        db.collection("users")
            .get()
            .addOnCompleteListener {

                val result: StringBuffer = StringBuffer()

                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        result.append(document.data.getValue("namaMahasiswa")).append(" ")
                            .append(document.data.getValue("nim")).append("\n\n")
                            .append(document.data.getValue("ipk")).append("\n\n")
                    }
                    textViewResult.setText(result)
                }
            }


    }
}