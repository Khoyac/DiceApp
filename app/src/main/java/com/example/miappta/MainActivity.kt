package com.example.miappta

import android.Manifest
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.miappta.ui.main.SectionsPagerAdapter
import com.example.miappta.databinding.ActivityMainBinding
import android.view.MenuInflater
import android.widget.Toast
import androidx.annotation.NonNull
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.miappta.resources.Dados
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        var ListaDados: MutableList<Dados> = mutableListOf()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNeedPermissions()

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
                cambiarIdioma(Locale("en", "EN"))
                Toast.makeText(applicationContext, "click on hola", Toast.LENGTH_LONG).show()
                true
            }
            R.id.caracola ->{
                cambiarIdioma(Locale("pi", "PI"))
                Toast.makeText(applicationContext, "click on caracola", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.esp ->{
                cambiarIdioma(Locale("es", "ES"))
                Toast.makeText(applicationContext, "click on caracola", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.captura ->{
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val bm: Bitmap = screenShot(this.findViewById(R.id.fragmentResultados))
                val file: File = saveBitmap(bm, "$timeStamp.png")
                Log.i("chase", "filepath: " + file.absolutePath)
                val uri: Uri = Uri.fromFile(File(file.absolutePath))
//                val shareIntent = Intent()
//                shareIntent.action = Intent.ACTION_SEND
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my app.")
//                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
//                shareIntent.type = "image/*"
//                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                startActivity(Intent.createChooser(shareIntent, "share via"))
                Toast.makeText(applicationContext, R.string.capGuardada, Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cambiarIdioma(localizacion: Locale) {
        Locale.setDefault(localizacion)
        val config = Configuration()
        config.setLocale(localizacion)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val refresh = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(refresh)
        finish()
    }

    private fun screenShot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveBitmap(bm: Bitmap, fileName: String): File {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/Screenshots"
        val dir = File(path)
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, fileName)
        try {
            val fOut = FileOutputStream(file)
            bm.compress(Bitmap.CompressFormat.PNG, 90, fOut)
            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }

    private fun checkNeedPermissions() {
        // Por encima de 6.0 debe solicitar el permiso de forma dinámica
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            // Solicite varios permisos juntos
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 1
            )
        }
    }

    fun abrirPopUp(fragment: Fragment, id: Int) {
        val fragmentIntercambio = supportFragmentManager.beginTransaction()
        fragmentIntercambio.replace(id, fragment)
        fragmentIntercambio.commit()
    }



    }