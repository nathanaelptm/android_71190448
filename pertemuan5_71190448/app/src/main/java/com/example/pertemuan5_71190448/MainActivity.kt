package com.example.pertemuan5_71190448

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.username)
            val password = findViewById<TextInputEditText>(R.id.password)
            //if(username.text.trim().isNotEmpty() || username.text.trim().isNotEmpty()){
            //    Toast.makeText(this, "input provided",Toast.LENGTH_LONG).show()
            //}else{
            //    Toast.makeText(this,"Input Required", Toast.LENGTH_LONG).show()
            //}
            if(username.text.toString().equals("ahsipan") && password.text.toString().equals("12345")){
                Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                    Toast.LENGTH_SHORT).show();
                val intent = Intent(this, HalamanUtama::class.java)
                intent.putExtra("username","ahsipan")
                startActivity(intent)
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Username atau Password Anda salah!")
                    .setNegativeButton("Retry", null).create().show()
            }
        }
        }


    }