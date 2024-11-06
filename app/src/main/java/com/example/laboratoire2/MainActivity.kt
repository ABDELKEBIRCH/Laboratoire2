package com.example.laboratoire2

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laboratoire2.DestinataireActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val CLE_COULEUR = "COULEUR"
    private var mCouleur = 0

    private lateinit var layoutBase: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var btnExplicit: Button
    private lateinit var btnRes: Button  // Ajout de l'initialisation manquante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialiser les vues
        layoutBase = findViewById(R.id.layoutBase)
        checkBox = findViewById(R.id.checkbox)
        btnExplicit = findViewById(R.id.btnExplicit)
        btnRes = findViewById(R.id.btnRes)  // Ajout de l'initialisation manquante

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")

        val btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener { finish() }

        mCouleur = Random(System.currentTimeMillis()).nextInt()

        btnExplicit.setOnClickListener(this)
        btnRes.setOnClickListener (this)
    }

    override fun onClick(view: View?) {
        when(view){
            btnExplicit ->{
                val intent = Intent(this@MainActivity, DestinataireActivity::class.java)
                startActivity(intent)
            }
            btnRes ->{
                val intent = Intent(this, DestinataireResActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        when (requestCode) {
            1 -> {
                val resTxt = if (resultCode == Activity.RESULT_OK) {
                    "RESULT_OK " + DestinataireResActivity.CLE1 + "= " +
                            data?.getStringExtra(DestinataireResActivity.CLE1) + " " +
                            DestinataireResActivity.CLE2 + "= " +
                            data?.getBooleanExtra(DestinataireResActivity.CLE2, false)
                } else
                    "RESULT_CANCELED"
                Toast.makeText(this, resTxt, Toast.LENGTH_LONG).show()
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this,
            "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()
        if(checkBox.isChecked) {
            mCouleur = savedInstanceState.getInt(CLE_COULEUR)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
        layoutBase.setBackgroundColor(mCouleur)

    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MainActivity ", "dans ${object {}.javaClass.enclosingMethod.name}")
        Toast.makeText(this,
            "dans ${object {}.javaClass.enclosingMethod.name}",Toast.LENGTH_LONG).show()
        if(checkBox.isChecked){
            outState.putInt(CLE_COULEUR, (layoutBase.background as ColorDrawable).color)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "dans ${object {}.javaClass.enclosingMethod.name}")
    }

    fun onClickButtonAction1(view: View) {
        val intent = Intent("com.example.laboratoire2.ACTION1")
        intent.addCategory("com.example.laboratoire2.CATEGORIE1")
        startActivity(intent)
    }
    fun onClickButtonAction2(view: View) {
        val intent = Intent("com.example.laboratoire2.ACTION2")
        intent.addCategory("android.intent.category.DEFAULT")
        startActivity(intent)
    }

    fun onClickButtonActionView(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://www.cgodin.qc.ca")
        startActivity(intent)
    }

}
