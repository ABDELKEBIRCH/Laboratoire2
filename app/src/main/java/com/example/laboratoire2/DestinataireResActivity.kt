package com.example.laboratoire2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DestinataireResActivity : AppCompatActivity() {
    companion object{
        val CLE1 = "CLE1"
        val CLE2 = "CLE2"
    }

    private lateinit var btnResultat: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_destinataire_res)

        setResult(Activity.RESULT_CANCELED)

        btnResultat = findViewById(R.id.btnResultat)

        btnResultat.setOnClickListener {
            val donnees = Intent()
            donnees.putExtra(CLE1, "allo")
            donnees.putExtra(CLE2, true)
            setResult(Activity.RESULT_OK, donnees)
            finish()
        }

        }


}
