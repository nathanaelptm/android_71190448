package id.ac.ukdw.fti.final_71190448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if(user != null){
                val dashboardIntent = Intent(this, Dashboard::class.java)
                startActivity(dashboardIntent)
            }else{
                val signinIntent = Intent(this, SignIn::class.java)
                startActivity(signinIntent)
            }
        }, 2000)
    }
}