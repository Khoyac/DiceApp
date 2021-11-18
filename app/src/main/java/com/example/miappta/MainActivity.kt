package com.example.miappta

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.miappta.ui.main.SectionsPagerAdapter
import com.example.miappta.databinding.ActivityMainBinding
import com.example.miappta.R
import android.view.MenuInflater
import android.widget.Toast
import androidx.annotation.NonNull


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.hola -> {
                Toast.makeText(applicationContext, "click on hola", Toast.LENGTH_LONG).show()
                true
            }
            R.id.carapolla ->{
                Toast.makeText(applicationContext, "click on carapolla", Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun abrirPopUp(fragment: Fragment, id: Int) {
        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace(id, fragment)
        fragmentIntercambio.commit()
    }

}