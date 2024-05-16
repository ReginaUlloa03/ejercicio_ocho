package com.example.ejercicio_ocho

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun save(v: View){

        val name = findViewById<EditText>(R.id.txtName)
        val phoneNumber = findViewById<EditText>(R.id.txtPhoneNomber)
        val contact = Contact(name.text.toString(), phoneNumber.text.toString())
        ProvicionalData.listContact.add(contact)

        val control = FileHandling(this)
        val gson = Gson()
        val content = gson.toJson(ProvicionalData.listContact)
        val result = control.save(content)
        
        val msg = if (result) "Save" else "Error"
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        finish()

    }
}