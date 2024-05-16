package com.example.ejercicio_ocho

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Array
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var rcv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rcv = findViewById(R.id.rvContact)

    }

    override fun onResume() {
        super.onResume()

        val fileHandling = FileHandling(this)
        val jsonContent = fileHandling.readFile()

        // Verificar si el contenido JSON no es nulo
        if (jsonContent.isNotEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Contact>>() {}.type
            ProvicionalData.listContact = gson.fromJson(jsonContent, type)

            Log.w("Contact", "Hay ${ProvicionalData.listContact.size} registros de contactos")
            rcv.adapter = Adapter(this)
            rcv.layoutManager = LinearLayoutManager(this)
        } else {
            // Si el contenido JSON es nulo, muestra un mensaje de error o realiza alguna otra acción
            Log.e("MainActivity", "El contenido JSON es nulo")
        }
    }

    fun btnAdd(v: View) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun clickItem(position: Int) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

}