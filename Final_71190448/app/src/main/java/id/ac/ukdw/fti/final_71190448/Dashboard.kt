package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class Dashboard : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var pendudukRecyclerView : RecyclerView
    private lateinit var pendudukArrayList : ArrayList<Penduduk>
    private lateinit var dbase : DatabaseReference
    private lateinit var myAdapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val refresh = findViewById<SwipeRefreshLayout>(R.id.refresh)
        refresh.setOnRefreshListener {
            Toast.makeText(this,"Refreshed", Toast.LENGTH_SHORT).show()
            refresh.isRefreshing = false
            pendudukRecyclerView = findViewById(R.id.recyclerview)
            pendudukRecyclerView.layoutManager = LinearLayoutManager(this)
            pendudukRecyclerView.setHasFixedSize(true)

            pendudukArrayList = arrayListOf()
            myAdapter = MyAdapter(pendudukArrayList)
            pendudukRecyclerView.adapter = myAdapter
            getUserData()
        }
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val name = findViewById<TextView>(R.id.name_txt)
        val profile_image = findViewById<ImageView>(R.id.profile_image)
        name.text = currentUser?.displayName

        pendudukRecyclerView = findViewById(R.id.recyclerview)
        pendudukRecyclerView.layoutManager = LinearLayoutManager(this)
        pendudukRecyclerView.setHasFixedSize(true)

        pendudukArrayList = arrayListOf()
        myAdapter = MyAdapter(pendudukArrayList)
        pendudukRecyclerView.adapter = myAdapter
        getUserData()

        Glide.with(this).load(currentUser?.photoUrl).into(profile_image)
        val btnSignOut = findViewById<Button>(R.id.btnSignOut)
        val btnToAdd = findViewById<Button>(R.id.btnToAdd)
        val btnToSearch = findViewById<Button>(R.id.btnToSearch)
        val btnToUpdate = findViewById<Button>(R.id.btnToUpdate)
        val btnToDelete = findViewById<Button>(R.id.btnToDelete)
        btnSignOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }
        btnToAdd.setOnClickListener {
            val intent = Intent(this, AddPenduduk::class.java)
            startActivity(intent)
        }
        btnToSearch.setOnClickListener {
            val intent = Intent(this, ReadPenduduk::class.java)
            startActivity(intent)

        }
        btnToUpdate.setOnClickListener {
            val intent = Intent(this, UpdatePendudukSingle::class.java)
            startActivity(intent)

        }
        btnToDelete.setOnClickListener {
            val intent = Intent(this, DeletePenduduk::class.java)
            startActivity(intent)

        }

    }
    private fun getUserData(){
        dbase = FirebaseDatabase.getInstance().getReference("Penduduk")
        dbase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val penduduk = userSnapshot.getValue(Penduduk::class.java)
                        pendudukArrayList.add(penduduk!!)
                    }
                    pendudukRecyclerView.adapter = MyAdapter(pendudukArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}