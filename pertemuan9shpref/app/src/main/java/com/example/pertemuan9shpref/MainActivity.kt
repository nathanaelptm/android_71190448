package com.example.pertemuan9shpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    var sp: SharedPreferences?=null
    var spEdit:SharedPreferences.Editor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("mySP",Context.MODE_PRIVATE)
        spEdit = sp?.edit()
        if(sp?.getBoolean("isLogin",false) == true){
            //login
            setContentView(R.layout.activity_halaman_utama)
            val spinnerBahasa = findViewById<Spinner>(R.id.spinnerBahasa)
            val adapter = ArrayAdapter.createFromResource(this, R.array.list_bahasa, R.layout.support_simple_spinner_dropdown_item)

            spinnerBahasa.adapter = adapter
            spinnerBahasa.setSelection(sp!!.getInt("bahasa",0))
            spinnerBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    spEdit?.putInt("bahasa", position)
                    spEdit?.commit()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            val ukuran = findViewById<EditText>(R.id.etUkuran)
            ukuran.setText(sp!!.getString("ukuran","10"))
            ukuran.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran", s.toString() )
                    spEdit?.commit()
                }
            })

        }else{
            setContentView(R.layout.activity_main)
            val username =  findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener{
                if(username.text.toString()=="ahsipan" && password.text.toString()=="12345"){
                    spEdit?.putBoolean("isLogin", true)
                    spEdit?.commit()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}