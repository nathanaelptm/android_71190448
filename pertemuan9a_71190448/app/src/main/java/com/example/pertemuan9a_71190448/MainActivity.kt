package com.example.pertemuan9a_71190448

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val listFragment: ArrayList<Fragment> = arrayListOf(FragmentSatu(), FragmentDua(), FragmentTiga())
        val pagerAdapter = PagerAdapter(this, listFragment)
        viewPager.adapter = pagerAdapter


    }
    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>):FragmentStateAdapter(activity){
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment.get(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_default,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.menu_profile -> {
                Toast.makeText(this,"Fragment Satu", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WadahFragment::class.java)
                startActivity(intent)
                true
            }R.id.menu_settings -> {
                Toast.makeText(this, "Fragment 2", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WadahFragment2::class.java)
                startActivity(intent)
                true
            }R.id.menu_mantap -> {
                Toast.makeText(this, "Fragment 3", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WadahFragment3::class.java)
                startActivity(intent)
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }

        }
    }


    }