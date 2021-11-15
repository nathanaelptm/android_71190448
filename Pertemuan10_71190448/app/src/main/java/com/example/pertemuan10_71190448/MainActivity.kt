package com.example.pertemuan10_71190448

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.database.getStringOrNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var dbHelper: SQLiteOpenHelper? = null
    var db: SQLiteDatabase? = null
    var listPenduduk = ArrayList<String>()
    var adapter: PendudukAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        db = dbHelper?.writableDatabase

        val edtNama = findViewById<TextView>(R.id.etNama)
        val edtUsia = findViewById<TextView>(R.id.etUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        btnSimpan.setOnClickListener{
            val values = ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, edtNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, edtUsia.text.toString())
            }
            db?.insert(DatabaseContract.Penduduk.TABLE_NAME,null, values)
            edtNama.setText("")
            edtUsia.setText("")
            reloadData()

        }
        btnCari.setOnClickListener{
            cari(Penduduk(edtNama.text.toString(), edtUsia.text.toString().toInt()))
            edtNama.setText("")
            edtUsia.setText("")
        }


        val rcyPenduduk = findViewById<RecyclerView>(R.id.rcyPenduduk)
        rcyPenduduk.layoutManager = LinearLayoutManager(this)
        adapter = PendudukAdapter(listPenduduk, db)
        rcyPenduduk.adapter = adapter
        reloadData()
    }
    fun reloadData(){
        listPenduduk.clear()

        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor!!){
            while(moveToNext()){
                val nama = getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getStringOrNull(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                listPenduduk.add("${nama}(${usia})")
            }
            adapter?.notifyDataSetChanged()
        }
    }
    fun cari(penduduk: Penduduk) {
        listPenduduk.clear()
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND" +
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(penduduk.nama, penduduk.usia.toString())

        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0), getInt(1))
                result += "${penduduk.nama} - ${penduduk.usia}\n"
            }
        }
        val txvInfo = findViewById<TextView>(R.id.txvInfo)
        txvInfo.text = result
    }
}