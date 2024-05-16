package com.example.ejercicio_ocho

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class EditActivity : AppCompatActivity() {

    var position: Int = 0
    lateinit var txtName: EditText
    lateinit var txtPhoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = "Modify contact"
        position = intent.getIntExtra("position", -1)
        Log.e("Contact", "Se recibio un ${position}")
        txtName = findViewById(R.id.txtName)
        txtPhoneNumber = findViewById(R.id.txtPhoneNomber)

        val contact = ProvicionalData.listContact[position]
        txtName.setText(contact.name)
        txtPhoneNumber.setText(contact.phoneNumber)

    }
    fun save(v: View) {
        val contact = Contact(txtName.text.toString(), txtPhoneNumber.text.toString())
        ProvicionalData.listContact[position] = contact

        val json = Gson()
        val jsonContent = json.toJson(ProvicionalData.listContact)

        val fileHandling = FileHandling(this)
        val result = fileHandling.save(jsonContent)

        val msg = if (result) "Save" else "Error"

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        finish()
    }
}