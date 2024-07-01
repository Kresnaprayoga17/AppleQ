package com.example.appleq

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnTentang = findViewById<CardView>(R.id.btnAbout)
        btnTentang.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val btnDataset = findViewById<CardView>(R.id.btnDataset)
        btnDataset.setOnClickListener {
            val intent = Intent(this, DatasetActivity::class.java)
            startActivity(intent)
        }

        val btnFitur = findViewById<CardView>(R.id.btnFitur)
        btnFitur.setOnClickListener {
            val intent = Intent(this, FiturActivity::class.java)
            startActivity(intent)
        }

        val btnModel = findViewById<CardView>(R.id.btnModel)
        btnModel.setOnClickListener {
            val intent = Intent(this, ModelActivity::class.java)
            startActivity(intent)
        }

        val btnSimulasiModel = findViewById<CardView>(R.id.btnSimulasi)
        btnSimulasiModel.setOnClickListener {
            val intent = Intent(this, SimulasiActivity::class.java)
            startActivity(intent)
        }
    }
}
